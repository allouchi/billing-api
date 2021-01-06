package com.aliateck.fact.infrastructure.adapter.facture;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.edition.BuildFactureService;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.CommonSpiEntityService;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import com.aliateck.fact.infrastructure.repository.prestation.PrestationJpaRepository;
import com.aliateck.util.UtilsFacture;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureSpiAdapter implements FactureSpiService {
	FactureJpaRepository factureJpaRepository;
	PrestationJpaRepository prestationJpaRepository;
	BuildFactureService calculerFactureService;
	FactureMapper factureMapper;
	PrestationMapper prestationMapper;
	CommonSpiEntityService commonSpiEntityService;

	@Override
	public Facture addFacture(String siret, Facture facture, Long prestationId) {
		Facture fDomain = null;
		if (facture.getId() != null && facture.getId().longValue() == 0) {
			facture.setId(null);
		}

		FactureEntity factureModif = null;
		String numFactureModif = null;
		String numeroCommande = null;

		PrestationEntity prestaEntity = commonSpiEntityService.findPrestationById(siret, prestationId);

		if (prestaEntity != null) {
			Prestation prestation = prestationMapper.fromEntityToDomain(prestaEntity);
			Facture factureCaculee = calculerFactureService.calculerFacture(prestation, facture);
			FactureEntity factEntity = factureMapper.fromDomainToEntity(factureCaculee);
			prestaEntity.getFacture().add(factEntity);
			PrestationEntity pSaved = prestationJpaRepository.save(prestaEntity);

			List<FactureEntity> fEntities = pSaved.getFacture();
			if (fEntities != null && !fEntities.isEmpty()) {
				Iterator<FactureEntity> it = fEntities.iterator();
				while (it.hasNext()) {
					FactureEntity entity = it.next();
					if (entity != null && entity.getNumeroCommande() != null
							&& entity.getNumeroCommande().equalsIgnoreCase(facture.getNumeroCommande())) {
						String numeroFacture = entity.getNumeroFacture();
						String newNumFacture = UtilsFacture.updateNumeroFacture(numeroFacture,
								entity.getId().longValue());
						numFactureModif = newNumFacture;
						factureModif = entity;
						numeroCommande = entity.getNumeroCommande();
					}
				}
			}
			if (factureModif != null) {
				factureModif.setNumeroFacture(numFactureModif);
				pSaved.getFacture().add(factureModif);
				pSaved.setNumeroCommande(numeroCommande);
				PrestationEntity oEntity = prestationJpaRepository.save(pSaved);
				if (oEntity.getFacture() != null && !oEntity.getFacture().isEmpty()) {
					for (FactureEntity fact : oEntity.getFacture()) {
						if (fact != null && fact.getNumeroCommande().equalsIgnoreCase(facture.getNumeroCommande())) {
							fDomain = factureMapper.fromEntityToDomain(fact);
						}
					}
				}
			}
		}
		return fDomain;
	}

	@Override
	public List<Facture> findAllByPrestation(String siret, Long idPrestation) {
		List<FactureEntity> entities = commonSpiEntityService.findFacturesByPrestation(siret, idPrestation);
		if (entities != null && !entities.isEmpty()) {
			return factureMapper.fromEntityToDomain(entities);
		}
		return Collections.emptyList();
	}

	@Override
	public void deleteFactureById(String siret, Long prestationId, Long factureId) {
		FactureEntity factureEntity = commonSpiEntityService.findFactureById(siret, prestationId, factureId);
		if(factureEntity != null && factureEntity.getId() != null) {
			factureJpaRepository.deleteById(factureId);
		}
		
	}

	@Override
	public void deleteFacture(String siret, Facture facture, Long prestationId) {
		factureJpaRepository.delete(factureMapper.fromDomainToEntity(facture));
	}

	@Override
	public Facture updateFacture(String siret, Facture facture, Long prestationId) {

		return null;
	}

	@Override
	public Facture findById(Long id) {
		Optional<FactureEntity> entity = factureJpaRepository.findById(id);
		if (entity.isPresent()) {
			return factureMapper.fromEntityToDomain(entity.get());
		} else {
			throw new FactureNotFoundException("Facture not found");
		}
	}

	@Override
	public Facture findByNumeroFacture(String numeroFacture) {
		FactureEntity entity = factureJpaRepository.getByNumeroFacture(numeroFacture);
		if (entity == null) {
			throw new FactureNotFoundException("Facture not found avec numero : " + numeroFacture);
		}
		return factureMapper.fromEntityToDomain(entity);
	}

	@Override
	public List<Facture> findByFactureStatus(boolean statusFacture) {
		List<FactureEntity> entitys = factureJpaRepository.findByFactureStatus(statusFacture);
		if (entitys == null || entitys.isEmpty()) {
			throw new FactureNotFoundException("Facture not found avec status : " + statusFacture);
		}
		return factureMapper.fromEntityToDomain(entitys);
	}

	@Override
	public List<Facture> findByDateEcheance(Date dateEcheance) {
		List<FactureEntity> entities = factureJpaRepository.findByDateEcheance(dateEcheance);

		if (entities == null || entities.isEmpty()) {
			throw new FactureNotFoundException("Facture not found avec date echeance : " + dateEcheance);
		}
		return factureMapper.fromEntityToDomain(entities);
	}

	@Override
	public List<Facture> findByDateEncaissement(Date dateEncaissement) {
		List<FactureEntity> entitys = factureJpaRepository.findByDateEncaissement(dateEncaissement);
		if (entitys == null || entitys.isEmpty()) {
			throw new FactureNotFoundException("Facture not found avec date encaissement : " + dateEncaissement);
		}
		return factureMapper.fromEntityToDomain(entitys);
	}

	@Override
	public List<Facture> findAllBySiret(String siret) {
		List<FactureEntity> entities = commonSpiEntityService.findAllFacturesBySiret(siret);
		return factureMapper.fromEntityToDomain(entities);
	}

	
}

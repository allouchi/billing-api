package com.aliateck.fact.infrastructure.adapter.facture;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.mapping.AccessOptions.SetOptions.Propagation;
import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.edition.BuildFactureService;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.EntitySpiService;
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
	EntitySpiService entitySpiService;

	@Override
	public Facture addFacture(String siret, Facture facture, Long prestationId) {
		Facture fDomain = null;
		if (facture.getId() != null && facture.getId().longValue() == 0) {
			facture.setId(null);
		}
				
		PrestationEntity prestaEntity = entitySpiService.findPrestationById(siret, prestationId);
		List<FactureEntity> listeFacture = entitySpiService.findFacturesByPrestation(siret, prestationId);
		
		if (prestaEntity != null) {
			Prestation prestation = prestationMapper.fromEntityToDomain(prestaEntity);
			Facture factureCaculee = calculerFactureService.calculerFacture(prestation, facture);
			FactureEntity factEntity = factureMapper.fromDomainToEntity(factureCaculee);
			String numeroFacture = UtilsFacture.updateNumeroFacture(factureMapper.fromEntityToDomain(listeFacture));
			factEntity.setNumeroFacture(numeroFacture);
			FactureEntity factureSeved = factureJpaRepository.save(factEntity);
			prestaEntity.getFacture().add(factureSeved);			
			PrestationEntity pSaved = prestationJpaRepository.save(prestaEntity);

			List<FactureEntity> fEntities = pSaved.getFacture();
			if (fEntities != null && !fEntities.isEmpty()) {
				for(FactureEntity entity : fEntities) {
					if(entity.getNumeroFacture() !=null && entity.getNumeroFacture().equals(numeroFacture)) {
						fDomain = factureMapper.fromEntityToDomain(factEntity);	
					}
				}				
			}			
		}
		return fDomain;
	}

	@Override
	public List<Facture> findAllByPrestation(String siret, Long idPrestation) {
		List<FactureEntity> entities = entitySpiService.findFacturesByPrestation(siret, idPrestation);
		if (entities != null && !entities.isEmpty()) {
			return factureMapper.fromEntityToDomain(entities);
		}
		return Collections.emptyList();
	}

	@Override
	public void deleteFactureById(String siret, Long prestationId, Long factureId) {
		FactureEntity factureEntity = entitySpiService.findFactureById(siret, prestationId, factureId);
		if(factureEntity != null && factureEntity.getId() != null) {
			factureJpaRepository.deleteById(factureId);
			factureJpaRepository.flush();			
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
		List<FactureEntity> entities = entitySpiService.findAllFacturesBySiret(siret);
		return factureMapper.fromEntityToDomain(entities);
	}

	
}

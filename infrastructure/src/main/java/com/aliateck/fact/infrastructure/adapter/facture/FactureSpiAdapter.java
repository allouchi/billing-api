package com.aliateck.fact.infrastructure.adapter.facture;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.edition.BuildFactureService;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.EntitySpiService;
import com.aliateck.fact.infrastructure.mapper.CompanyMapper;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.edition.EditionReportService;
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
	BuildFactureService buildFactureService;
	EditionReportService editionReportService;
	CompanyJpaRepository companyJpaRepository;
	CompanyMapper companyMapper;

	@Override
	public Prestation addFacture(String siret, Prestation prestation, Long prestationId, String pathRoot) {
		
		
		Optional<CompanyEntity> cEntity = companyJpaRepository.findBySiret(siret);
		PrestationEntity prestaEntity = entitySpiService.findPrestationById(siret, prestationId);
		List<FactureEntity> listeFacture = entitySpiService.findFacturesByPrestation(siret, prestationId);

		if (cEntity.isPresent()) {
			CompanyEntity oEntity = cEntity.get();
			Company company = companyMapper.fromEntityToDomain(oEntity);
			Prestation oPrestation = prestationMapper.fromEntityToDomain(prestaEntity);
			Client client = oPrestation.getClient();
			Facture factureEditee = calculerFactureService.buildFacture(siret, prestation);
			String numeroFacture = UtilsFacture.updateNumeroFacture(client.getSocialReason().toLowerCase(), factureMapper.fromEntityToDomain(listeFacture));
			factureEditee.setNumeroFacture(numeroFacture);
			Map<String, Object> paramJasper = editionReportService.buildParamJasper(company, prestation, factureEditee);
			FactureEntity factEntity = factureMapper.fromDomainToEntity(factureEditee);
			String fileName = (String) paramJasper.get("fileName");
			String pathFile = buildFactureService.buildPathFile(siret, pathRoot, client.getSocialReason().toLowerCase());
			editionReportService.buildPdfFacture(paramJasper, pathFile);
			String pathToSave = UtilsFacture.buildPath(pathFile, pathRoot);
			factEntity.setFilePath(pathToSave + "\\" + fileName);			
			prestaEntity.getFacture().add(factEntity);			
			prestaEntity.setNumeroCommande(prestation.getNumeroCommande());
			prestaEntity.setDesignation(prestation.getDesignation());
			prestaEntity.setClientPrestation(prestation.getClientPrestation());
			PrestationEntity pEntity = prestationJpaRepository.save(prestaEntity);
			return prestationMapper.fromEntityToDomain(pEntity);			
		}
		
		return null;
		
	}	

	@Override
	public void deleteFacture(Long factureId) {
		factureJpaRepository.deleteById(factureId);
	}

	@Override
	public Facture updateFacture(Facture factureRequest) {	
		if(factureRequest == null || factureRequest.getId().longValue() == 0) {
			return null;
		}
		
		Optional<FactureEntity> entity  = factureJpaRepository.findById(factureRequest.getId());		
		if (entity.isPresent()) {
			Facture facture = factureMapper.fromEntityToDomain(entity.get());
			Facture oFacture = UtilsFacture.updateFacture(facture, factureRequest);
			FactureEntity fEntity = factureMapper.fromDomainToEntity(oFacture);
			FactureEntity oEntity = factureJpaRepository.save(fEntity); 
			return factureMapper.fromEntityToDomain(oEntity);
		} else {
			throw new FactureNotFoundException("Facture not found");
		}		
		
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

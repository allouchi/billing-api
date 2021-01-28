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
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.EntitySpiService;
import com.aliateck.fact.infrastructure.adapter.consultant.ConsultantSpiAdapter;
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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
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
		if (factureRequest == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}
		try {
			Optional<FactureEntity> entity  = factureJpaRepository.findById(factureRequest.getId());		
			if (entity.isPresent()) {
				Facture facture = factureMapper.fromEntityToDomain(entity.get());
				Facture oFacture = UtilsFacture.updateFacture(facture, factureRequest);
				FactureEntity fEntity = factureMapper.fromDomainToEntity(oFacture);
				FactureEntity oEntity = factureJpaRepository.save(fEntity); 
				return factureMapper.fromEntityToDomain(oEntity);
			}
	
} catch (ServiceException e) {
	
	throw e;
}
		
		catch (Exception e) {
			log.error("error while updating facture with requested ID:" + "" + factureRequest.getId(), e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, e);
		}
		return null;
				
		
	}

	@Override
	public Facture findById(Long id) {
		Optional<FactureEntity> entity = factureJpaRepository.findById(id);
		if (entity.isPresent()) {
			return factureMapper.fromEntityToDomain(entity.get());
		} 
		return null;
	}

	@Override
	public Facture findByNumeroFacture(String numeroFacture) {
		FactureEntity entity = factureJpaRepository.getByNumeroFacture(numeroFacture);
		
		return factureMapper.fromEntityToDomain(entity);
	}

	@Override
	public List<Facture> findByFactureStatus(boolean statusFacture) {
		List<FactureEntity> entitys = factureJpaRepository.findByFactureStatus(statusFacture);
		
		return factureMapper.fromEntityToDomain(entitys);
	}

	@Override
	public List<Facture> findByDateEcheance(Date dateEcheance) {
		List<FactureEntity> entities = factureJpaRepository.findByDateEcheance(dateEcheance);

		
		return factureMapper.fromEntityToDomain(entities);
	}

	@Override
	public List<Facture> findByDateEncaissement(Date dateEncaissement) {
		List<FactureEntity> entitys = factureJpaRepository.findByDateEncaissement(dateEncaissement);
		
		return factureMapper.fromEntityToDomain(entitys);
	}

	@Override
	public List<Facture> findAllBySiret(String siret) {
		List<FactureEntity> entities = entitySpiService.findAllFacturesBySiret(siret);
		return factureMapper.fromEntityToDomain(entities);		
	}

}

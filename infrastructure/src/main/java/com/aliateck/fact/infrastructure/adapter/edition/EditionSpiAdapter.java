package com.aliateck.fact.infrastructure.adapter.edition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.edition.BuildFactureService;
import com.aliateck.fact.domaine.ports.spi.edition.EditionSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.EntitySpiService;
import com.aliateck.fact.infrastructure.mapper.CompanyMapper;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.edition.EditionReportService;
import com.aliateck.fact.infrastructure.repository.prestation.PrestationJpaRepository;

import groovy.util.logging.Slf4j;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionSpiAdapter implements EditionSpiService {
	EditionReportService editionReportService;
	CompanyJpaRepository companyJpaRepository;
	EntitySpiService commonSpiEntityService;
	PrestationJpaRepository prestationJpaRepository;
	BuildFactureService buildFactureService;
	CompanyMapper companyMapper;
	PrestationMapper prestationMapper;
	FactureMapper factureMapper;

	@Override
	public Facture buildFacture(String siret, long prestationId, Facture facture) {
		List<FactureEntity> listeFacture = new ArrayList<>();
		PrestationEntity prestationEntity = commonSpiEntityService.findPrestationById(siret, prestationId);
		Prestation prestation = prestationMapper.fromEntityToDomain(prestationEntity);

		if (prestation != null && facture != null) {
			Facture factureCalculee = buildFactureService.calculerFacture(prestation, facture);

			FactureEntity entity = factureMapper.fromDomainToEntity(factureCalculee);
			listeFacture.add(entity);
			prestationEntity.setFacture(listeFacture);
			PrestationEntity pEbtity = prestationJpaRepository.saveAndFlush(prestationEntity);
			for (FactureEntity factEntity : pEbtity.getFacture()) {
				if (factEntity.getNumeroFacture().equalsIgnoreCase(facture.getNumeroFacture())) {
					return factureMapper.fromEntityToDomain(factEntity);
				}
			}
		}

		return null;
	}

	@Override
	public Map<String, Object> editerFacture(String siret, long prestationId, Facture facture) {

		byte[] pdfBinary = null;

		Map<String, Object> map = new HashMap<>();
		List<FactureEntity> listeFacture = new ArrayList<>();

		Optional<CompanyEntity> cEntity = companyJpaRepository.findBySiret(siret);
		if (cEntity.isPresent()) {
			CompanyEntity oEntity = cEntity.get();
			PrestationEntity prestationEntity = commonSpiEntityService.findPrestationById(siret, prestationId);
			if (prestationEntity != null) {

				Company company = companyMapper.fromEntityToDomain(oEntity);
				Prestation prestation = prestationMapper.fromEntityToDomain(prestationEntity);
				Facture factureEditee = buildFactureService.buildFacture(siret, prestation, facture);
				FactureEntity entity = factureMapper.fromDomainToEntity(factureEditee);
				Map<String, Object> paramJasper = editionReportService.buildParamJasper(company, prestation,
						factureEditee);
				pdfBinary = editionReportService.buildPdfFacture(paramJasper, factureEditee.getFilePath());
				// entity.setFileContent(fileBinary);
				listeFacture.add(entity);
				prestationEntity.setFacture(listeFacture);
				prestationJpaRepository.save(prestationEntity);

				Facture factureFinal = factureMapper.fromEntityToDomain(entity);
				map.put("facture", factureFinal);
				map.put("pdf", pdfBinary);
			}
		}
		return map;
	}
}

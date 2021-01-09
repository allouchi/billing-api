package com.aliateck.fact.infrastructure.adapter.edition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jfree.util.Log;
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
		
		PrestationEntity prestationEntity = commonSpiEntityService.findPrestationById(siret, prestationId);
		Prestation prestation = prestationMapper.fromEntityToDomain(prestationEntity);

		if (prestation != null && facture != null) {
			Facture factureCalculee = buildFactureService.calculerFacture(prestation, facture);
			FactureEntity entity = factureMapper.fromDomainToEntity(factureCalculee);
			prestationEntity.getFacture().add(entity);
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
	public Facture editerFacture(String siret, long prestationId, Facture facture) {

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
				String fileName = (String) paramJasper.get("fileName");
				String pathFile = buildFactureService.buildPathFile(siret);
				editionReportService.buildPdfFacture(paramJasper, pathFile);
				entity.setFilePath(pathFile + "\\" + fileName);
				//entity.setFilePath("test.pdf");
				prestationEntity.getFacture().add(entity);
				PrestationEntity pEntity = prestationJpaRepository.save(prestationEntity);

				for (FactureEntity fact : pEntity.getFacture()) {
					if (fact.getId().longValue() == facture.getId().longValue()) {
						return factureMapper.fromEntityToDomain(fact);
					}

				}

			}
		}
		return null;
	}

	@Override
	public byte[] downloadPdf(String path) {

		try {
			Path pathFile = Paths.get(path);
			return Files.readAllBytes(pathFile);
		} catch (IOException e) {
			Log.debug("Pdf not found : " + e.getMessage());
		}
		return new byte[0];
	}
}

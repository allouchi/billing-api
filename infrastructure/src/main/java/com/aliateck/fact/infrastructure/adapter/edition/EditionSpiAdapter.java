package com.aliateck.fact.infrastructure.adapter.edition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

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
	public Facture editerFacture(String siret, Long prestationId, Facture facture, String pathRoot) {

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
				String directoryPdf = pathRoot + "\\" + pathFile;
				editionReportService.buildPdfFacture(paramJasper, directoryPdf);
				entity.setFilePath(pathFile + "\\" + fileName);

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
	public byte[] downloadPdf(String siret, Long prestationId, Long factureId, String rootDirectory) {

		FactureEntity facture = commonSpiEntityService.findFactureById(siret, prestationId, factureId);
		if (facture != null) {
			String path = facture.getFilePath();
			String pathComplet = rootDirectory + "\\" + path;

			try {
				Path pathFile = Paths.get(pathComplet);
				return Files.readAllBytes(pathFile);
			} catch (IOException e) {
				log.debug("Pdf not found : " + e.getMessage());
			}
		}
		return new byte[0];
	}
}

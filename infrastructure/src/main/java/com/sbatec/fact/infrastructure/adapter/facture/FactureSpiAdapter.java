package com.sbatec.fact.infrastructure.adapter.facture;

import com.sbatec.fact.domaine.business.object.Company;
import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.business.object.Prestation;
import com.sbatec.fact.domaine.common.edition.BuildFactureService;
import com.sbatec.fact.domaine.exception.ErrorCatalog;
import com.sbatec.fact.domaine.exception.ServiceException;
import com.sbatec.fact.domaine.ports.spi.facture.FactureSpiService;
import com.sbatec.fact.infrastructure.adapter.commun.EntitySpiService;
import com.sbatec.fact.infrastructure.mapper.CompanyMapper;
import com.sbatec.fact.infrastructure.mapper.FactureMapper;
import com.sbatec.fact.infrastructure.mapper.PrestationMapper;
import com.sbatec.fact.infrastructure.models.CompanyEntity;
import com.sbatec.fact.infrastructure.models.FactureEntity;
import com.sbatec.fact.infrastructure.models.PrestationEntity;
import com.sbatec.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.sbatec.fact.infrastructure.repository.edition.EditionReportService;
import com.sbatec.fact.infrastructure.repository.facture.FactureJpaRepository;
import com.sbatec.fact.infrastructure.repository.prestation.PrestationJpaRepository;
import com.sbatec.util.Utils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
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
    public Prestation addFacture(String siret, boolean newTemplate, Prestation prestation, String pathRoot,
                                 Long moisFactureId, Boolean storeFile, String fileSuivi) {

        byte[] binaryPdf = null;
        if (Objects.isNull(prestation) || Objects.isNull(siret) || Objects.isNull(pathRoot) || Objects.isNull(moisFactureId)
                || Objects.isNull(newTemplate) || Objects.isNull(storeFile)) {
            throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
        }
        if (!Objects.isNull(prestation.getId()) && prestation.getId() == 0) {
            prestation.setId(null);
        }
        String moisFacture = Utils.convertMoisFacture(String.valueOf(moisFactureId));

        try {

            Optional<CompanyEntity> cEntity = companyJpaRepository.findBySiret(siret);
            PrestationEntity prestaEntity = entitySpiService.findPrestationById(siret, prestation.getId());
            List<FactureEntity> listeFacture = entitySpiService.findFacturesByPrestation(siret, prestation.getId());
            Optional.of(cEntity).orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND));

            Company company = companyMapper.fromEntityToDomain(cEntity.get());
            Prestation oPrestation = prestationMapper.fromEntityToDomain(prestaEntity);
            Facture factureEditee = calculerFactureService.buildFacture(siret, prestation, moisFacture);
            String numeroFacture = Utils.updateNumeroFacture(factureEditee.getClientPrestation().toLowerCase(),
                    factureMapper.fromEntityToDomain(listeFacture), moisFactureId);
            factureEditee.setNumeroFacture(numeroFacture);

            FactureEntity factEntity = factureMapper.fromDomainToEntity(factureEditee);

            String pathFile = buildFactureService.buildPathFile(siret, pathRoot,
                    oPrestation.getClient().getSocialReason().toLowerCase(), moisFacture, moisFactureId);
            Map<String, Object> dataPdf = null;
            if (newTemplate) {
                dataPdf = editionReportService.buildParamsNewTemplate(company, prestation, factureEditee);
                binaryPdf = editionReportService.buildFacturePdFSaucer(dataPdf, pathFile, storeFile);
            } else {
                dataPdf = editionReportService.buildParamJasper(company, prestation, factureEditee);
                binaryPdf = editionReportService.buildPdfFacture(dataPdf, pathFile,
                        storeFile);
            }
            String fileName = (String) dataPdf.get("fileName");
            String pathToSave = Utils.buildPath(pathFile, pathRoot);
            factEntity.setFilePath(pathToSave + File.separator + fileName);
            factEntity.setTarifHT(prestation.getTarifHT());
            factEntity.setFileContent(binaryPdf);
            factEntity.setFileName(fileName);
            factEntity.setMontantTVA(factEntity.getPrixTotalHT() * 0.2f);
            factEntity.setMontantNetTVA(factEntity.getMontantTVA() - 30);
            prestaEntity.getFactures().add(factEntity);
            prestaEntity.setNumeroCommande(prestation.getNumeroCommande());
            prestaEntity.setDesignation(prestation.getDesignation());
            prestaEntity.setClientPrestation(prestation.getClientPrestation());
            prestaEntity.setDateDebut(prestation.getDateDebut());
            prestaEntity.setDateFin(prestation.getDateFin());

            PrestationEntity pEntity = prestationJpaRepository.save(prestaEntity);
            List<FactureEntity> listeFactures = entitySpiService.findAllFacturesBySiret(siret);
            List<Facture> suiviFactures = factureMapper.fromEntityToDomain(listeFactures);
            String pathSuivi = Utils.buildPathSuivi(pathRoot, fileSuivi);
            editionReportService.buildSuiviFactures(suiviFactures, pathSuivi);
            return prestationMapper.fromEntityToDomain(pEntity);

        } catch (Exception e) {
            log.error("error while creating new facture : " + e.getMessage());
            throw new ServiceException(ErrorCatalog.DB_ERROR, "Un problème est survenu lors de l'édition de la facture",
                    e);
        }
    }

    @Override
    public Facture updateFacture(Facture factureRequest, String rootPath, String fileSuiviName) {

        Optional.ofNullable(factureRequest).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));

        try {
            FactureEntity entity = factureJpaRepository.findById(factureRequest.getId()).orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND));
            Facture facture = factureMapper.fromEntityToDomain(entity);
            Facture oFacture = Utils.updateFacture(facture, factureRequest);
            FactureEntity fact = factureMapper.fromDomainToEntity(oFacture);
            factureJpaRepository.saveAndFlush(fact);
            List<FactureEntity> listeFactures = entitySpiService.findAllFactures();
            List<Facture> suiviFactures = factureMapper.fromEntityToDomain(listeFactures);
            String pathSuivi = Utils.buildPathSuivi(rootPath, fileSuiviName);
            editionReportService.buildSuiviFactures(suiviFactures, pathSuivi);
            return factureMapper.fromEntityToDomain(fact);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("error while updating facture with requested ID:" + factureRequest.getId(), e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la mise à jour de la facture", e);
        }
    }

    @Override
    public void deleteFacture(Long factureId) {

        Optional.ofNullable(factureId).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        try {
            factureJpaRepository.deleteById(factureId);
        } catch (Exception e) {
            log.error("error while deleting facture with requested ID:" + factureId, e);
            throw new ServiceException(ErrorCatalog.DB_ERROR, e);
        }
    }

    @Override
    public Facture findById(Long id) {

        Optional.ofNullable(id).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));

        try {
            Optional<FactureEntity> entity = factureJpaRepository.findById(id);
            Optional.of(entity)
                    .orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucune facture enregistrée !"));
            return entity.map(factureMapper::fromEntityToDomain).orElse(null);
        } catch (Exception e) {
            log.error("error while updating facture with requested ID:" + id, e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la recherche de la facture", e);
        }
    }

    @Override
    public Facture findByNumeroFacture(String numeroFacture) {
        Optional.ofNullable(numeroFacture).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        FactureEntity entity = factureJpaRepository.getByNumeroFacture(numeroFacture);
        Optional.ofNullable(entity).orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND));
        return factureMapper.fromEntityToDomain(entity);
    }

    @Override
    public List<Facture> findAllBySiret(String siret) {
        Optional.ofNullable(siret).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));

        try {
            List<FactureEntity> entities = entitySpiService.findAllFacturesBySiret(siret);
            Optional.ofNullable(entities).orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND));
            return entities.stream().map((entity) ->
                            factureMapper.fromEntityToDomain(entity))
                    .sorted(Comparator.comparingLong(Facture::getId).reversed())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("error while retrieving facture with requested siret :" + siret, e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la recherche de la facture", e);
        }
    }

}

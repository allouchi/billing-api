package com.aliateck.fact.infrastructure.adapter.facture;

import java.io.File;
import java.util.Date;
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
import com.aliateck.util.Utils;
import lombok.AccessLevel;
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
  public Prestation addFacture(String siret, boolean templateChoice, Prestation prestation,
      String pathRoot, Long moisFactureId, boolean storeFile, String fileSuivi) {
    Prestation reponse = null;
    if (prestation == null || siret == null || siret.equals("") || pathRoot == null) {
      throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
    }

    if (prestation.getId() != null && prestation.getId() == 0) {
      prestation.setId(null);
    }
    

    String moisFacture = Utils.convertMoisFacture(String.valueOf(moisFactureId));

    try {

      Optional<CompanyEntity> cEntity = companyJpaRepository.findBySiret(siret);
      PrestationEntity prestaEntity =
          entitySpiService.findPrestationById(siret, prestation.getId());
      List<FactureEntity> listeFacture =
          entitySpiService.findFacturesByPrestation(siret, prestation.getId());

      if (cEntity.isPresent()) {
        CompanyEntity oEntity = cEntity.get();
        Company company = companyMapper.fromEntityToDomain(oEntity);
        Prestation oPrestation = prestationMapper.fromEntityToDomain(prestaEntity);
        Client client = oPrestation.getClient();
        Facture factureEditee = calculerFactureService.buildFacture(siret, prestation, moisFacture);
        String numeroFacture = Utils.updateNumeroFacture(factureEditee.getClientPrestation().toLowerCase(),
            factureMapper.fromEntityToDomain(listeFacture), moisFactureId);
        factureEditee.setNumeroFacture(numeroFacture);
        Map<String, Object> paramJasper = editionReportService.buildParamJasper(company,
            templateChoice, prestation, factureEditee);
        FactureEntity factEntity = factureMapper.fromDomainToEntity(factureEditee);
        String fileName = (String) paramJasper.get("fileName");
        String pathFile = buildFactureService.buildPathFile(siret, pathRoot,
            client.getSocialReason().toLowerCase(), moisFacture, moisFactureId);
        byte[] binaryPdf =
            editionReportService.buildPdfFacture(paramJasper, templateChoice, pathFile, storeFile);
        String pathToSave = Utils.buildPath(pathFile, pathRoot);
        factEntity.setFilePath(pathToSave + File.separator + fileName);
        factEntity.setTarifHT(prestation.getTarifHT());
        factEntity.setFileContent(binaryPdf);
        factEntity.setFileName(fileName);
        prestaEntity.getFacture().add(factEntity);
        prestaEntity.setNumeroCommande(prestation.getNumeroCommande());
        prestaEntity.setDesignation(prestation.getDesignation());
        prestaEntity.setClientPrestation(prestation.getClientPrestation());
        prestaEntity.setDateDebut(prestation.getDateDebut());
        prestaEntity.setDateFin(prestation.getDateFin());
        PrestationEntity pEntity = prestationJpaRepository.save(prestaEntity);
        reponse = prestationMapper.fromEntityToDomain(pEntity);

        List<FactureEntity> listeFactures = entitySpiService.findAllFacturesBySiret(siret);
        List<Facture> suiviFactures = factureMapper.fromEntityToDomain(listeFactures);
        String pathSuivi = Utils.buildPathSuivi(pathRoot, fileSuivi);
        editionReportService.buildSuiviFactures(suiviFactures, pathSuivi);
      }

    } catch (Exception e) {
      log.error("error while creating new facture");
      throw new ServiceException(ErrorCatalog.DB_ERROR,
          "Un problème est survenu lors de l'édition de la facture", e);
    }

    return reponse;

  }

  @Override
  public Facture updateFacture(Facture factureRequest, String rootPath, String fileSuiviName) {
    Facture reponse = null;
    if (factureRequest == null) {
      throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
    }
    try {
      Optional<FactureEntity> entity = factureJpaRepository.findById(factureRequest.getId());
      if (entity.isPresent()) {
        Facture facture = factureMapper.fromEntityToDomain(entity.get());
        Facture oFacture = Utils.updateFacture(facture, factureRequest);
        FactureEntity fEntity = factureMapper.fromDomainToEntity(oFacture);
        factureJpaRepository.saveAndFlush(fEntity);       
      
        List<FactureEntity> listeFactures = entitySpiService.findAllFactures();
        List<Facture> suiviFactures = factureMapper.fromEntityToDomain(listeFactures);
        String pathSuivi = Utils.buildPathSuivi(rootPath, fileSuiviName);
        editionReportService.buildSuiviFactures(suiviFactures, pathSuivi);

      }

    } catch (ServiceException e) {
      throw e;
    } catch (Exception e) {
      log.error("error while updating facture with requested ID:" + "" + factureRequest.getId(), e);
      throw new ServiceException(ErrorCatalog.DB_ERROR,
          "Un problème est survenu lors de la mise à jour de la facture", e);
    }
    return reponse;

  }

  @Override
  public void deleteFacture(Long factureId) {

    if (factureId == null) {
      throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
    }
    try {
      factureJpaRepository.deleteById(factureId);
    } catch (Exception e) {
      log.error("error while deleting facture with requested ID:" + "" + factureId, e);
      throw new ServiceException(ErrorCatalog.DB_ERROR, e);
    }

  }

  @Override
  public Facture findById(Long id) {
    Facture reponse = null;
    if (id == null) {
      throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
    }
    try {
      Optional<FactureEntity> entity = factureJpaRepository.findById(id);
      if (entity.isPresent()) {
        reponse = factureMapper.fromEntityToDomain(entity.get());
      }
    } catch (Exception e) {
      log.error("error while updating facture with requested ID:" + "" + id, e);
      throw new ServiceException(ErrorCatalog.DB_ERROR,
          "Un problème est survenu lors de la recherche de la facture", e);
    }

    if (reponse == null) {
      throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucune facture enregistrée !");
    }
    return reponse;

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
    List<Facture> reponse = null;
    if (siret == null) {
      throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
    }
    try {
      List<FactureEntity> entities = entitySpiService.findAllFacturesBySiret(siret);
      reponse = factureMapper.fromEntityToDomain(entities);
    } catch (Exception e) {
      log.error("error while retrieving facture with requested siret :" + "" + siret, e);
      throw new ServiceException(ErrorCatalog.DB_ERROR,
          "Un problème est survenu lors de la recherche de la facture", e);
    }
    if (reponse == null || reponse.isEmpty()) {
      throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucune facture enregistrée !");
    }
    return reponse;

  }

}

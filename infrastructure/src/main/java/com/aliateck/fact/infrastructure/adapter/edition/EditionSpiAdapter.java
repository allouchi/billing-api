package com.aliateck.fact.infrastructure.adapter.edition;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.FactureStatus;
import com.aliateck.fact.domaine.common.edition.CalculerFactureService;
import com.aliateck.fact.domaine.ports.spi.edition.EditionSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.CommonSpiEntityService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionSpiAdapter implements EditionSpiService {
  EditionReportService editionReportService;
  CompanyJpaRepository companyJpaRepository;
  CommonSpiEntityService commonSpiEntityService;
  CalculerFactureService calculerFactureService;
  PrestationJpaRepository prestationJpaRepository;
  CompanyMapper companyMapper;
  PrestationMapper prestationMapper;
  FactureMapper factureMapper;
 

  @Override
  public Facture buildFacture(String siret, long prestationId, Facture facture) {
    List<FactureEntity> listeFacture = new ArrayList<>();
    PrestationEntity prestationEntity = commonSpiEntityService.findPrestationById(
      siret,
      prestationId
    );
    Prestation prestation = prestationMapper.fromEntityToDomain(prestationEntity);

    if (prestation != null && facture != null) {
      Facture factureCalculee = calculerFactureService.calculerFacture(
        prestation,
        facture
      );

      FactureEntity entity = factureMapper.fromDomainToEntity(factureCalculee);
      listeFacture.add(entity);
      prestationEntity.setFactures(listeFacture);
      PrestationEntity pEbtity = prestationJpaRepository.saveAndFlush(prestationEntity);
      for (FactureEntity factEntity : pEbtity.getFactures()) {
        if (factEntity.getNumeroFacture().equalsIgnoreCase(facture.getNumeroCommande())) {
          return factureMapper.fromEntityToDomain(factEntity);
        }
      }
    }

    return null;
  }

  @Override
  public Map<String, Object> editerFacture(String siret, long prestationId, Facture facture) {
	byte[] pdf;
	Map<String, Object> data = new HashMap<String, Object>();
	
    List<FactureEntity> listeFacture = new ArrayList<>();
    Optional<CompanyEntity> cEntity = companyJpaRepository.findBySiret(siret);
    if (cEntity.isPresent()) {
      CompanyEntity oEntity = cEntity.get();
      PrestationEntity prestationEntity = commonSpiEntityService.findPrestationById(siret, prestationId);

      Company company = companyMapper.fromEntityToDomain(oEntity);
      Prestation prestation = prestationMapper.fromEntityToDomain(prestationEntity);
      Facture factureEditee = calculerFactureService.editerFacture(prestation, facture);
      FactureEntity entity = factureMapper.fromDomainToEntity(factureEditee);
      listeFacture.add(entity);
      prestationEntity.setFactures(listeFacture);
      prestationJpaRepository.save(prestationEntity);
      pdf = editionReportService.genererPdfFacture(company, prestation, factureEditee);
      data.put("facture", factureEditee);
      data.put("pdf", pdf);
      return data;
    }
    return null;
  }
}

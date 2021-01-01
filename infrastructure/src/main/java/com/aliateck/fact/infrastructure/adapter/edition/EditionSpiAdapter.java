package com.aliateck.fact.infrastructure.adapter.edition;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.FactureStatus;
import com.aliateck.fact.domaine.ports.spi.edition.EditionSpiService;
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

import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionSpiAdapter implements EditionSpiService {
  EditionReportService editionReportService;
  CompanyJpaRepository companyJpaRepository;
  FactureJpaRepository factureJpaRepository;
  CompanyMapper companyMapper;
  PrestationMapper prestationMapper;
  FactureMapper factureMapper;

  @Override
  public byte[] editerFacture(String siret, long prestationId, long factureId) {
    Optional<CompanyEntity> oEntity = companyJpaRepository.findBySiret(siret);
    if (oEntity.isPresent()) {
      CompanyEntity entity = oEntity.get();
      for (PrestationEntity presta : entity.getPrestations()) {
        if (
          presta.getId().longValue() == prestationId &&
          presta.getFacture().getId().longValue() == factureId
        ) {
        	
          Optional<FactureEntity> oFacture = factureJpaRepository.findById(factureId);
          FactureEntity eFacture = oFacture.get();
          eFacture.setFactureStatus(FactureStatus.OUI.getCode());
          FactureEntity bFacture  = factureJpaRepository.save(eFacture);
          Company company = companyMapper.fromEntityToDomain(oEntity.get());
          Prestation prestation = prestationMapper.fromEntityToDomain(presta);
          Facture facture = factureMapper.fromEntityToDomain(bFacture);
          return editionReportService.editerFacture(company, prestation, facture);
        }
      }
    }
    return new byte[0];
  }
}

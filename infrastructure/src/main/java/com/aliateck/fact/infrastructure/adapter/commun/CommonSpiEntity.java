package com.aliateck.fact.infrastructure.adapter.commun;

import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
public class CommonSpiEntity implements CommonSpiEntityService {
  CompanyJpaRepository companyJpaRepository;

  @Override
  public List<PrestationEntity> findAllPrestation(String siret) {
    Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
    if (oCompany.isPresent()) {
      CompanyEntity oEntity = oCompany.get();
      return oEntity.getPrestations();
    }
    return Collections.emptyList();
  }

  @Override
  public List<FactureEntity> findFacturesByPrestation(String siret, long prestationId) {
    Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
    if (oCompany.isPresent()) {
      CompanyEntity cEntity = oCompany.get();
      for (PrestationEntity prestations : cEntity.getPrestations()) {
        if (prestations.getId().longValue() == prestationId) {
          return prestations.getFactures();
        }
      }
    }
    return Collections.emptyList();
  }

  @Override
  public PrestationEntity findPrestationById(String siret, long prestationId) {
    List<PrestationEntity> prestations = findAllPrestation(siret);
    if (prestations != null && !prestations.isEmpty()) {
      for (PrestationEntity entity : prestations) {
        if (entity.getId().longValue() == prestationId) {
          return entity;
        }
      }
    }

    return null;
  }

  @Override
  public FactureEntity findFactureById(String siret, long prestationId, long factureId) {
    Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
    if (oCompany.isPresent()) {
      CompanyEntity cEntity = oCompany.get();
      for (PrestationEntity prestations : cEntity.getPrestations()) {
        if (prestations.getId().longValue() == prestationId) {
          for (FactureEntity facture : prestations.getFactures()) {
            if (facture.getId().longValue() == factureId) {
              return facture;
            }
          }
        }
      }
    }
    return null;
  }

  @Override
  public List<FactureEntity> findAllFacturesBySiret(String siret) {
    List<FactureEntity> listeFacture = new ArrayList<>();
    Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
    if (oCompany.isPresent()) {
      CompanyEntity cEntity = oCompany.get();
      for (PrestationEntity prestations : cEntity.getPrestations()) {
        for (FactureEntity factures : prestations.getFactures()) {
          listeFacture.add(factures);
        }
      }
    }
    return listeFacture;
  }
}

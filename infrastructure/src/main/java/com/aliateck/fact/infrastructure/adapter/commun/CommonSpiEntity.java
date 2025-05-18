package com.aliateck.fact.infrastructure.adapter.commun;

import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommonSpiEntity implements EntitySpiService {
    CompanyJpaRepository companyJpaRepository;
    FactureJpaRepository factureJpaRepository;


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
    public PrestationEntity findPrestationById(String siret, Long prestationId) {
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
    public FactureEntity findFactureById(Long factureId) {
        Optional<FactureEntity> entity = factureJpaRepository.findById(factureId);
        if (entity.isPresent()) {
            return entity.get();
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

    @Override
    public List<FactureEntity> findAllFactures() {
        return factureJpaRepository.findAll();
    }
}

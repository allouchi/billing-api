package com.aliateck.fact.infrastructure.adapter.prestation;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.edition.CalculerFactureService;
import com.aliateck.fact.domaine.ports.spi.prestation.PrestationSpiService;
import com.aliateck.fact.infrastructure.mapper.CompanyMapper;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.ConsultantEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import com.aliateck.fact.infrastructure.repository.prestation.PrestationJpaRepository;
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
public class PrestationSpiAdapter implements PrestationSpiService {
  PrestationJpaRepository prestationJpaRepository;
  CompanyJpaRepository companyJpaRepository;
  PrestationMapper prestationMapper;
  //EditionFactureService editionFactureService;
  CalculerFactureService calculerFactureService;
  FactureJpaRepository factureJpaRepository;
  FactureMapper factureMapper;
  CompanyMapper companyMapper;

  @Override
  public Prestation addPrestation(Prestation prestation, String siret) {
    Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);

    return oCompany
      .map(
        company -> {
          List<PrestationEntity> prestations = company.getPrestations();
          PrestationEntity entity = prestationMapper.fromDomainToEntity(prestation);
          prestations.add(entity);
          company.setPrestations(prestations);
          companyJpaRepository.save(company);
          PrestationEntity savedPrestation = company
            .getPrestations()
            .stream()
            .filter(
              c ->
                c
                  .getClient()
                  .getSocialReason()
                  .equals(prestation.getClient().getSocialReason())
            )
            .findFirst()
            .orElseGet(null);
          return prestationMapper.fromEntityToDomain(savedPrestation);
        }
      )
      .orElse(null);
  }

  @Override
  public void deletePrestation(Prestation prestation) {
    PrestationEntity entity = prestationMapper.fromDomainToEntity(prestation);
    prestationJpaRepository.delete(entity);
  }

  @Override
  public Prestation updatePrestation(Prestation prestation, String siret) {
    Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
    if (oCompnay.isPresent()) {
      CompanyEntity entity = oCompnay.get();
      List<PrestationEntity> oPrestation = entity.getPrestations();
      for (PrestationEntity c : oPrestation) {
        if (c.getId().longValue() == prestation.getId().longValue()) {
          PrestationEntity nEntity = prestationMapper.fromDomainToEntity(prestation);
          PrestationEntity domain = prestationJpaRepository.save(nEntity);
          return prestationMapper.fromEntityToDomain(domain);
        }
      }
    }
    return null;
  }

  @Override
  public Prestation findById(long id) {
    Optional<PrestationEntity> entity = prestationJpaRepository.findById(id);
    if (entity.isPresent()) {
      prestationMapper.fromEntityToDomain(entity.get());
    }

    return null;
  }

  @Override
  public List<Prestation> findAll() {
    List<PrestationEntity> entities = prestationJpaRepository.findAll();
    return prestationMapper.fromEntityToDomain(entities);
  }

  @Override
  public void deleteById(long id) {
    prestationJpaRepository.deleteById(id);
  }
}

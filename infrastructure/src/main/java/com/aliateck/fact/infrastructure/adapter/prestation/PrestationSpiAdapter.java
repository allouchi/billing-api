package com.aliateck.fact.infrastructure.adapter.prestation;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.spi.prestation.PrestationSpiService;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.prestation.PrestationJpaRepository;
import java.util.ArrayList;
import java.util.Collections;
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

  @Override
  public Prestation addPrestation(Prestation prestation, String siret) {
    if (prestation.getId() != null && prestation.getId().longValue() == 0) {
      prestation.setId(null);
    }
    List<PrestationEntity> prestations = new ArrayList<>();
    PrestationEntity entity = prestationMapper.fromDomainToEntity(prestation);

    Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
    if (oCompany.isPresent()) {
      prestations.add(entity);
      CompanyEntity cEntity = oCompany.get();
      cEntity.setPrestations(prestations);
      CompanyEntity cEntitySaved = companyJpaRepository.save(cEntity);
      List<PrestationEntity> savedPrestations = cEntitySaved.getPrestations();
      if (savedPrestations != null && !savedPrestations.isEmpty()) {
        for (PrestationEntity c : savedPrestations) {
          if (c.getNumeroCommande().equals(prestation.getNumeroCommande())) {
            return prestationMapper.fromEntityToDomain(c);
          }
        }
      }
    }
    return null;
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
  public List<Prestation> findAll(String siret) {
    Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
    if (oCompnay.isPresent()) {
      CompanyEntity entity = oCompnay.get();
      return prestationMapper.fromEntityToDomain(entity.getPrestations());
    }
    return Collections.emptyList();
  }

  @Override
  public void deleteById(long id) {
    prestationJpaRepository.deleteById(id);
  }
}

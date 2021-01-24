package com.aliateck.fact.infrastructure.adapter.prestation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.spi.prestation.PrestationSpiService;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.prestation.PrestationJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

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
    
    PrestationEntity pEntity = prestationMapper.fromDomainToEntity(prestation);
    Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
    
    if (oCompany.isPresent()) {      
      CompanyEntity cEntity = oCompany.get();
      cEntity.getPrestations().add(pEntity);
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
	  PrestationEntity entity = prestationMapper.fromDomainToEntity(prestation);
	  PrestationEntity oEntity = prestationJpaRepository.save(entity);
	  return prestationMapper.fromEntityToDomain(oEntity);
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

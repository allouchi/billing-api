package com.aliateck.fact.infrastructure.adapter.prestation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.edition.CalculerFactureService;
import com.aliateck.fact.domaine.ports.spi.prestation.PrestationSpiService;
import com.aliateck.fact.infrastructure.mapper.CompanyMapper;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
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
              c -> c.getNumeroCommande().equals(prestation.getNumeroCommande())
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
  public Prestation updatePrestation(Prestation prestation) {
	PrestationEntity oPresta = null;		
	Optional<PrestationEntity> oPrestation = prestationJpaRepository.findById(prestation.getId());	
	
	if(oPrestation.isPresent()  ) {
		oPresta = prestationJpaRepository.saveAndFlush(oPrestation.get());				
	}
	return prestationMapper.fromEntityToDomain(oPresta);
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
}

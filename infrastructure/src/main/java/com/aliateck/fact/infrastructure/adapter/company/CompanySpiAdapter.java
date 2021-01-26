package com.aliateck.fact.infrastructure.adapter.company;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.exception.CompanyNotFoundException;
import com.aliateck.fact.domaine.ports.spi.company.CompanySpiService;
import com.aliateck.fact.infrastructure.mapper.ClientMapper;
import com.aliateck.fact.infrastructure.mapper.CompanyMapper;
import com.aliateck.fact.infrastructure.mapper.ConsultantMapper;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
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
public class CompanySpiAdapter implements CompanySpiService {
  CompanyJpaRepository companyJpaRepository;
  CompanyMapper companyMapper;
  ClientMapper clientMapper;
  ConsultantMapper consultantMapper;
  PrestationMapper prestationMapper;

  @Override
  public Company addCompany(Company company) {
    if (company.getId() != null && company.getId().longValue() == 0) {
      company.setId(null);
    }

    CompanyEntity entity = companyMapper.fromDomainToEntity(company);
    CompanyEntity baseEntity = companyJpaRepository.save(entity);
    return companyMapper.fromEntityToDomain(baseEntity);
  }

  @Override
  public Company updateCompany(Company company) {
	  if (company.getId() != null && company.getId().longValue() == 0) {
	      company.setId(null);
	    }

	    CompanyEntity entity = companyMapper.fromDomainToEntity(company);
	    CompanyEntity baseEntity = companyJpaRepository.save(entity);
	    return companyMapper.fromEntityToDomain(baseEntity);
  }

  @Override
  public List<Company> findAll() {
    List<CompanyEntity> listEnities = companyJpaRepository.findAll();
    return companyMapper.fromEntityToDomain(listEnities);
  }

  @Override
  public Company findById(Long id) {
    Optional<CompanyEntity> entity = companyJpaRepository.findById(id);

    if (entity.isPresent()) {
      return companyMapper.fromEntityToDomain(entity.get());
    } else {
      throw new CompanyNotFoundException("Company not found with id : " + id);
    }
  }

  @Override
  public Company findByReasonSocialIgnoreCase(String reasonSocial) {
    Optional<CompanyEntity> entity = companyJpaRepository.findBySocialReasonIgnoreCase(
      reasonSocial
    );
    if (entity.isPresent()) {
      return companyMapper.fromEntityToDomain(entity.get());
    } else {
      throw new CompanyNotFoundException(
        "Company not found with social reason  : " + reasonSocial
      );
    }
  }

  @Override
  public Company findBySiret(String siret) {
    Optional<CompanyEntity> entity = companyJpaRepository.findBySiret(siret);
    if (entity.isPresent()) {
      return companyMapper.fromEntityToDomain(entity.get());
    } else {
      throw new CompanyNotFoundException("Company not found with siret : " + siret);
    }
  }

  @Override
  public void deleteCompany(Long id) {
    companyJpaRepository.deleteById(id);
  }
}

package com.aliateck.fact.infrastructure.adapter.company;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.exception.CompanyNotFoundException;
import com.aliateck.fact.domaine.ports.spi.company.CompanySpiService;
import com.aliateck.fact.infrastructure.mapper.CompanyMapper;
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
  CompanyMapper mapper;

  @Override
  public void addCompany(Company company) {
    CompanyEntity entity = mapper.fromDomainToEntity(company);
    companyJpaRepository.save(entity);
  }

  @Override
  public void updateCompany(Company company) {
    CompanyEntity entity = mapper.fromDomainToEntity(company);
    Optional<CompanyEntity> objBase = companyJpaRepository.findById(company.getId());
    if (objBase.isPresent()) {
      CompanyEntity entityBase = objBase.get();

      entityBase.setId(entity.getId());
      entityBase.setApe(entity.getApe());
      entityBase.setRcsName(entity.getRcsName());
      entityBase.setSiret(entity.getSiret());
      entityBase.setSocialReason(entity.getSocialReason());
      entityBase.setStatus(entity.getStatus());
      entityBase.setTvaName(entity.getTvaName());
      companyJpaRepository.save(entityBase);
    }
  }

  @Override
  public void removeCompany(Company company) {
    CompanyEntity entity = mapper.fromDomainToEntity(company);
    companyJpaRepository.delete(entity);
  }

  @Override
  public List<Company> findAllCompanys() {
    List<CompanyEntity> listEnities = companyJpaRepository.findAll();
    return mapper.fromEntityToDomain(listEnities);
  }

  @Override
  public Company findCompanyById(long id) {
    Optional<CompanyEntity> entity = companyJpaRepository.findById(id);

    if (entity.isPresent()) {
      return mapper.fromEntityToDomain(entity.get());
    } else {
      throw new CompanyNotFoundException("Company not found with : " + id);
    }
  }

  @Override
  public Company findCompanyByReasonSocialIgnoreCase(String reasonSocial) {
    Optional<CompanyEntity> entity = companyJpaRepository.findBySocialReasonIgnoreCase(
      reasonSocial
    );
    if (entity.isPresent()) {
      return mapper.fromEntityToDomain(entity.get());
    } else {
      throw new CompanyNotFoundException("Company not found with : " + reasonSocial);
    }
  }

  @Override
  public Company findCompanyBySiret(String siret) {
    Optional<CompanyEntity> entity = companyJpaRepository.findBySiret(siret);
    if (entity.isPresent()) {
      return mapper.fromEntityToDomain(entity.get());
    } else {
      throw new CompanyNotFoundException("Company not found with : " + siret);
    }
  }
}

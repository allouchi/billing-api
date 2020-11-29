package com.aliateck.fact.infrastructure.adapter.company;

import com.aliateck.fact.domaine.business.object.Company;
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
    CompanyEntity entityClient = mapper.fromDomainToEntity(company);
    Optional<CompanyEntity> objBase = companyJpaRepository.findById(company.getId());
    if (objBase.isPresent()) {
      CompanyEntity entityBase = objBase.get();
      System.out.println("**********************************");
      System.out.println(entityBase.getId());
      System.out.println("**********************************");

      entityBase.setId(entityClient.getId());
      entityBase.setApe(entityClient.getApe());
      entityBase.setRcsName(entityClient.getRcsName());
      entityBase.setSiret(entityClient.getSiret());
      entityBase.setSocialReason(entityClient.getSocialReason());
      entityBase.setStatus(entityClient.getStatus());
      entityBase.setTvaName(entityClient.getTvaName());
      entityBase.setCompanyAdresse(entityClient.getCompanyAdresse());
      entityBase.setClients(entityClient.getClients());
      entityBase.setUsers(entityClient.getUsers());

      companyJpaRepository.save(entityBase);
    }
  }

  @Override
  public void removeCompany(Company company) {
    CompanyEntity entity = mapper.fromDomainToEntity(company);
    companyJpaRepository.delete(entity);
  }

  @Override
  public List<Company> getAllCompanys() {
    List<CompanyEntity> listEnities = companyJpaRepository.findAll();
    return mapper.fromEntityToDomainList(listEnities);
  }

  @Override
  public Company getCompanyById(long id) {
    Optional<CompanyEntity> entity = companyJpaRepository.findById(id);

    if (entity.isPresent()) {
      return mapper.fromEntityToDomain(entity.get());
    }
    return null;
  }

  @Override
  public Company getCompanyByReasonSocial(String reasonSocial) {
    CompanyEntity entity = companyJpaRepository.getCompanyBySocialReason(reasonSocial);
    return mapper.fromEntityToDomain(entity);
  }
}

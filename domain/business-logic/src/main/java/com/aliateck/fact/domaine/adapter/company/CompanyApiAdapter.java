package com.aliateck.fact.domaine.adapter.company;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.spi.company.CompanySpiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyApiAdapter implements CompanyApiService {
  CompanySpiService companySpiService;

  @Override
  public Company addCompany(Company company) {
    return companySpiService.addCompany(company);
  }

  @Override
  public void removeCompany(Company company) {
    companySpiService.removeCompany(company);
  }

  @Override
  public Company updateCompany(Company company) {
    return companySpiService.updateCompany(company);
  }

  @Override
  public List<Company> getAllCompanys() {
    return companySpiService.findAllCompanys();
  }

  @Override
  public Company getCompanyById(long id) {
    return companySpiService.findCompanyById(id);
  }

  @Override
  public Company getCompanyByReasonSocialIgnoreCase(String reasonSocial) {
    return companySpiService.findCompanyByReasonSocialIgnoreCase(reasonSocial);
  }

  @Override
  public Company getCompanyBySiret(String siret) {
    return companySpiService.findCompanyBySiret(siret);
  }
}

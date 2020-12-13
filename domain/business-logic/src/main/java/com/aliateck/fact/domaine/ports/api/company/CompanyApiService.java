package com.aliateck.fact.domaine.ports.api.company;

import com.aliateck.fact.domaine.business.object.Company;
import java.util.List;

public interface CompanyApiService {
  public void addCompany(Company company);

  public void removeCompany(Company company);

  public void updateCompany(Company company);

  public List<Company> getAllCompanys();

  public Company getCompanyById(long id);

  public Company getCompanyByReasonSocialIgnoreCase(String reasonSocial);

  public Company getCompanyBySiret(String siret);
}

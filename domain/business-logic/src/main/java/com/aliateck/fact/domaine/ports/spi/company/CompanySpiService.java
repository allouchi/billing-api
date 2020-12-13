package com.aliateck.fact.domaine.ports.spi.company;

import com.aliateck.fact.domaine.business.object.Company;
import java.util.List;

public interface CompanySpiService {
  public Company addCompany(Company company);

  public void removeCompany(Company company);

  public Company updateCompany(Company company);

  public List<Company> findAllCompanys();

  public Company findCompanyById(long id);

  public Company findCompanyByReasonSocialIgnoreCase(String reasonSocial);

  public Company findCompanyBySiret(String siret);
}

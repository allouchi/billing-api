package com.aliateck.fact.domaine.ports.spi.company;

import com.aliateck.fact.domaine.business.object.Company;
import java.util.List;

public interface CompanySpiService {
  public Company addCompany(Company company);  

  public Company updateCompany(Company company);

  public List<Company> findAll();

  public Company findById(long id);

  public Company findByReasonSocialIgnoreCase(String reasonSocial);

  public Company findBySiret(String siret);
  
  public void deleteById(long siret);
}

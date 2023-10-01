package com.aliateck.fact.domaine.ports.api.company;

import com.aliateck.fact.domaine.business.object.Company;

import java.util.List;

public interface CompanyApiService {
    public Company addCompany(Company company);

    public Company updateCompany(Company company);

    public List<Company> findAll();

    public Company findById(long id);

    public Company findByReasonSocialIgnoreCase(String reasonSocial);

    public Company findBySiret(String siret);

    public List<Company> findByUserName(String userName);

    public void deleteById(long id);
}

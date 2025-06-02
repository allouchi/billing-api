package com.sbatec.fact.domaine.ports.api.company;

import com.sbatec.fact.domaine.business.object.Company;

import java.util.List;

public interface CompanyApiService {
    public Company addCompany(Company company);

    public Company updateCompany(Company company);

    public List<Company> findAll();

    public Company findById(long id);

    public Company findBySiret(String siret);

    public void deleteById(long id);
}

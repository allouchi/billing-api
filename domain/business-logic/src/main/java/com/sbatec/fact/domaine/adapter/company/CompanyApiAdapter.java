package com.sbatec.fact.domaine.adapter.company;

import com.sbatec.fact.domaine.business.object.Company;
import com.sbatec.fact.domaine.ports.api.company.CompanyApiService;
import com.sbatec.fact.domaine.ports.spi.company.CompanySpiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Company updateCompany(Company company) {
        return companySpiService.updateCompany(company);
    }

    @Override
    public List<Company> findAll() {
        return companySpiService.findAll();
    }

    @Override
    public Company findById(long id) {
        return companySpiService.findById(id);
    }


    @Override
    public Company findBySiret(String siret) {
        return companySpiService.findBySiret(siret);
    }

    @Override
    public void deleteById(long id) {
        companySpiService.deleteCompany(id);
    }


}

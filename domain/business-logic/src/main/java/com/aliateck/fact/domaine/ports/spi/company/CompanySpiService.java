package com.aliateck.fact.domaine.ports.spi.company;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Company;

public interface CompanySpiService {
	public Company addCompany(Company company);

	public Company updateCompany(Company company);

	public List<Company> findAll();

	public Company findById(Long id);

	public Company findByReasonSocialIgnoreCase(String reasonSocial);

	public Company findBySiret(String siret);

	public void deleteCompany(Long id);

	public Company findByUserName(String userName);
}

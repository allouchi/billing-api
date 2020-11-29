package com.aliateck.fact.domaine.ports.spi.company;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Company;

public interface CompanySpiService {
	
	
	public void addCompany(Company company) ;

	public void removeCompany(Company company);

	public void updateCompany(Company company);

	public List<Company> getAllCompanys();

	public Company getCompanyById(long id);
	
	public Company getCompanyByReasonSocial(String reasonSocial);
	

}

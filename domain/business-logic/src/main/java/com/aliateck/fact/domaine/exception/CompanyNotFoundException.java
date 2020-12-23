package com.aliateck.fact.domaine.exception;

public class CompanyNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public CompanyNotFoundException(String siret) {
		super(String.format("No company finded for siret %s", siret));		
	}	


}

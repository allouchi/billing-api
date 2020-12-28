package com.aliateck.fact.domaine.exception;

public class ConsultantNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public ConsultantNotFoundException(String siret) {
		super(String.format("No consultant finded for id %s", siret));		
	}	

}


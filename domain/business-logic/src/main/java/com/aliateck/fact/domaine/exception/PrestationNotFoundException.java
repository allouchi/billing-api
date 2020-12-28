package com.aliateck.fact.domaine.exception;

public class PrestationNotFoundException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public PrestationNotFoundException(String id) {
		super(String.format("No prestation finded for siret %s", id));		
	}	

}


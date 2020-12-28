package com.aliateck.fact.domaine.exception;

public class FactureNotFoundException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public FactureNotFoundException(String message) {
		super(String.format("No bills finded for id  %s", message));		
	}	

}

package com.sbatec.fact.domaine.exception;

public class FactureNotFoundException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public FactureNotFoundException(String message) {
		super(message);		
	}	

}

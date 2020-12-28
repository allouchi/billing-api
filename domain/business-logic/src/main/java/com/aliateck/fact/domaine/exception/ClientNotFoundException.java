package com.aliateck.fact.domaine.exception;

public class ClientNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public ClientNotFoundException(String message) {
		super(String.format("No clients finded for id  %s", message));		
	}	

}

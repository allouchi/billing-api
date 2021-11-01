package com.aliateck.fact.domaine.exception;

public class FileNotFoundException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public FileNotFoundException(String message) {
		super(String.format("No clients finded for id  %s", message));		
	}	

}

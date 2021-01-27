package com.aliateck.fact.domaine.exception;

public class MethodArgumentNotValidException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public MethodArgumentNotValidException(String message) {
		super(String.format("No bills finded for id  %s", message));		
	}	
}
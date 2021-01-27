package com.aliateck.fact.domaine.exception;

public class UserAlreadyExistsException extends RuntimeException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public UserAlreadyExistsException(String id) {
		super(String.format("The user already exists in database %s", id));		
	}

}

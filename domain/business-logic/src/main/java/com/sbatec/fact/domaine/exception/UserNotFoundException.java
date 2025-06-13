package com.sbatec.fact.domaine.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

	private ErrorCatalog errorCatalog;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public UserNotFoundException(String message) {
		super(message);		
	}

	public UserNotFoundException(ErrorCatalog errorCatalog, String message) {
		super(message);
		this.errorCatalog = errorCatalog;
	}

}

package com.aliateck.fact.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aliateck.fact.domaine.exception.ClientNotFoundException;
import com.aliateck.fact.domaine.exception.CompanyNotFoundException;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.exception.PrestationNotFoundException;
import com.aliateck.fact.domaine.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerAdvicer {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFound(UserNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(FactureNotFoundException.class)
	public ResponseEntity<Object> factureNotFound(FactureNotFoundException ex) {		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<Object> companyNotFound(CompanyNotFoundException ex) {		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<Object> clientNotFound(ClientNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}


	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(PrestationNotFoundException.class)
	public ResponseEntity<Object> prestationNotFound(PrestationNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}

package com.aliateck.fact.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aliateck.fact.domaine.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerAdvicer {
		
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFound(UserNotFoundException ex) {		
		return ex.getMessage();
	}

}

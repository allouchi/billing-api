package com.aliateck.fact.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aliateck.fact.domaine.exception.ClientNotFoundException;
import com.aliateck.fact.domaine.exception.CompanyNotFoundException;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.exception.PrestationNotFoundException;
import com.aliateck.fact.domaine.exception.UserAlreadyExistsException;
import com.aliateck.fact.domaine.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

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

	@ResponseStatus(code = HttpStatus.FOUND)
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Object> userAlreadyExists(UserAlreadyExistsException ex) {		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}

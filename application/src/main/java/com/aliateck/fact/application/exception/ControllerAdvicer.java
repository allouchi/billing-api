package com.aliateck.fact.application.exception;

import com.aliateck.fact.domaine.exception.ClientNotFoundException;
import com.aliateck.fact.domaine.exception.CompanyNotFoundException;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvicer {

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)
  public String userNotFound(UserNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ResponseBody
  @ExceptionHandler(FactureNotFoundException.class)
  public String factureNotFound(FactureNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ResponseBody
  @ExceptionHandler(CompanyNotFoundException.class)
  public String companyNotFound(CompanyNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ResponseBody
  @ExceptionHandler(ClientNotFoundException.class)
  public String clientNotFound(ClientNotFoundException ex) {
    return ex.getMessage();
  }
}

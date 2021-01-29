package com.aliateck.fact.application.rest.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aliateck.fact.domaine.exception.ErrorBack;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {


  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<Object> handelServiceException(ServiceException exception) {
    ErrorCatalog errorCatalog = exception.getErrorCatalog();
    int code = errorCatalog.getCode();
    String message = exception.getMessage();
    ErrorBack errorDetails = new ErrorBack(code, message);
    log.error("Service Exception !", exception.getMessage());
    return new ResponseEntity<>(errorDetails, getHttpStatus(errorCatalog));
  }
  
  private HttpStatus getHttpStatus(ErrorCatalog errorCatalog) {
    switch (errorCatalog) {
      case BAD_DATA_ARGUMENT:
      case RESOURCE_NOT_FOUND:
        return HttpStatus.BAD_REQUEST;
      case ACCESS_DENIED:
        return HttpStatus.FORBIDDEN;
      case DUPLICATE_DATA:
        return HttpStatus.CONFLICT;
      default:
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }
}

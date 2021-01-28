package com.aliateck.fact.application.rest.exeptions;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

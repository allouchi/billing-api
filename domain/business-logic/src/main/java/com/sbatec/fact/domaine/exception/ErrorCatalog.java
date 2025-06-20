package com.sbatec.fact.domaine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCatalog {
    
    DB_ERROR("DB_ERROR", "Database error", ErrorLevel.TECHNICAL),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Resource not found ", ErrorLevel.FUNCIONAL),
    ACCESS_DENIED("ACCESS_DENIED", "Access is denied", ErrorLevel.FUNCIONAL),
    BAD_DATA_ARGUMENT("BAD_DATA_ARGUMENT", "Bad request", ErrorLevel.FUNCIONAL),
    DUPLICATE_DATA("DUPLICATE_DATA", "Duplicate data", ErrorLevel.FUNCIONAL),
    PDF_ERROR("PDF_ERROR", "PDF data", ErrorLevel.TECHNICAL);

    private String code;
    private String message;
    private ErrorLevel errorLevel;

}

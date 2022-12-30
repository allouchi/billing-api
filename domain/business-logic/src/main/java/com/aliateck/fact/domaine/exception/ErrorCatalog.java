package com.aliateck.fact.domaine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCatalog {

    APPLICATION_ERROR(1, "An error has occured..", ErrorLevel.TECHNICAL),
    DB_ERROR(2, "Database error", ErrorLevel.TECHNICAL),
    RESOURCE_NOT_FOUND(3, "Resource not found ", ErrorLevel.TECHNICAL),
    ACCESS_DENIED(4, "Access is denied", ErrorLevel.FUNCIONAL),
    BAD_DATA_ARGUMENT(5, "Bad request", ErrorLevel.FUNCIONAL),
    DUPLICATE_DATA(6, "Duplicate data", ErrorLevel.FUNCIONAL),
    PDF_ERROR(6, "PDF data", ErrorLevel.TECHNICAL);

    private int code;
    private String message;
    private ErrorLevel errorLevel;

}

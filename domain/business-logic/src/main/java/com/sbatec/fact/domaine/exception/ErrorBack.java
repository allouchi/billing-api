package com.sbatec.fact.domaine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorBack {
    private String code;
    private String message;
}

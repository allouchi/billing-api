package com.aliateck.fact.domaine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorBack {
  private int code;
  private String message;
}

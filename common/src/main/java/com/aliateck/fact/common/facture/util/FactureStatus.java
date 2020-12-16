package com.aliateck.fact.common.facture.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FactureStatus {
  OUI("OK", "Facture reglee"),
  NON("KO", "Facture n'est pas encore reglee");

  private String code;
  private String message;
}

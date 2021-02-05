package com.aliateck.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FactureStatus {
  OUI("OK", "Facture acquitté"),
  NON("KO", "Facture non acquitté");

  private String code;
  private String description;
}




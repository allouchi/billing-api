package com.aliateck.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FactureStatus {
  OUI("OK", "Facture acquittée"),
  NON("KO", "Facture non acquittée");

  private String code;
  private String description;
}




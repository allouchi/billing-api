package com.aliateck.fact.domaine.business.object;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Adresse {
  Long id;
  String complementAdresse;
  String numero;
  String voie;
  String codePostal;
  String commune;
  String pays;
}

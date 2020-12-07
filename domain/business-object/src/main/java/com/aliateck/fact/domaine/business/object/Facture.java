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
public class Facture {
  Long id;

  String numeroFacture;

  String dateFacturation;

  String dateEcheance;

  String dateEncaissement;

  float tarifHT;

  float tva;

  float prixTotalHT;

  float prixTotalTTC;

  long nbJourRetard;

  float fraisRetard;

  String factureStatus;

  Prestation prestation;
}

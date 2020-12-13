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
  Integer nbJoursEffectue;
  String dateFacturation;
  String dateEcheance;
  String dateEncaissement;
  Float tva;
  Float prixTotalHT;
  Float prixTotalTTC;
  Long nbJourRetard;
  Float fraisRetard;
  String factureStatus;
  Prestation prestation;
}

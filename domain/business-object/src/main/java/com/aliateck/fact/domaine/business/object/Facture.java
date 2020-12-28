package com.aliateck.fact.domaine.business.object;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Facture {
  Long id;
  String numeroFacture;
  String dateFacturation;
  String dateEcheance;
  String dateEncaissement;
  float montantTVA;
  float prixTotalHT;
  float prixTotalTTC;
  long nbJourRetard;
  float fraisRetard;
  long delaiPaiement;
  String factureStatus;
  String moisFacture;  
  float quantite;
  String designation;
  String numeroCommande;
}

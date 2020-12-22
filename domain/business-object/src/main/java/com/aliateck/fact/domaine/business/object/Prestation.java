package com.aliateck.fact.domaine.business.object;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Prestation {
  Long id;
  String numeroCommande;
  Consultant consultant;
  Client client;
  Facture facture;
  Integer tarifHT;
  Float nbJoursEffectues;
  Long delaiPaiement;
}

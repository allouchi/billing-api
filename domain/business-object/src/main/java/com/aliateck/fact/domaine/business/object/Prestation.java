package com.aliateck.fact.domaine.business.object;

import java.util.List;

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
  Consultant consultant;
  Client client;
  List<Facture> factures;
  int tarifHT;
  long delaiPaiement;  
  String numeroCommande;
}

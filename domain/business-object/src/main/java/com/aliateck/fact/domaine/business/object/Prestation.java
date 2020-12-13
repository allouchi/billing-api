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
public class Prestation {
  Long id;
  String numeroCommande;
  Consultant consultant;
  Client client;
  Integer tarifHT;
  Integer delaiPaiement;
}

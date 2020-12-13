package com.aliateck.fact.domaine.business.object;

import com.aliateck.fact.domaine.business.object.common.Domain;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company extends Domain {
  Long id;
  String socialReason;
  String status;
  String siret;
  String rcsName;
  String tvaName;
  String ape;
  Long delaiPaiement;
  Adresse companyAdresse;
  List<Client> clients;
  List<Consultant> consultant;
  List<Prestation> prestation;
}

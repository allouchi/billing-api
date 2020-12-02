package com.aliateck.fact.domaine.business.object;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {
  Long id;
  String socialReason;
  String status;
  String siret;
  String rcsName;
  String tvaName;
  String ape;

  CompanyAdresse companyAdresse;
  List<User> users;
  List<Client> clients;
  List<Consultant> consultant;
}

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
public class Client {
  Long id;
  String socialReason;
  Company company;
  ClientAdresse adresse;
  List<Consultant> consultants;
}

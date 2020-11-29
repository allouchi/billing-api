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
public class Consultant {
  Long id;
  String firstName;
  String lastName;
  String mail;

  List<Client> clients;
}

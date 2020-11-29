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
public class User {
  Long id;
  String firstName;
  String lastName;
  String email;
  String password;
  Company company;
}

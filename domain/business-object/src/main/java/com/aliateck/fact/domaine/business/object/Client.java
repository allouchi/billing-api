package com.aliateck.fact.domaine.business.object;

import com.aliateck.fact.domaine.business.object.common.Domain;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client extends Domain {
  Long id;
  String socialReason;
  Adresse adresse;
}

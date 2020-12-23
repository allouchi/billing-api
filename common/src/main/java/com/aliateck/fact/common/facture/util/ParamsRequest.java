package com.aliateck.fact.common.facture.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamsRequest {
  String siret;
  long idClient;
  long idConsultant;
}

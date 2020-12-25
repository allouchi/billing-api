package com.aliateck.fact.domaine.ports.api.edition;

public interface EditionApiService {
  public byte[] editerFacture(String siret, long idPrestation, long idFacture);
}

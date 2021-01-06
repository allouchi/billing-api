package com.aliateck.fact.domaine.ports.api.edition;

import java.util.Map;

import com.aliateck.fact.domaine.business.object.Facture;

public interface EditionApiService {
	
  public Map<String, Object> editerFacture(String siret, long idPrestation, Facture facture);
  public Facture buildFacture(String siret, long idPrestation, Facture facture);
}

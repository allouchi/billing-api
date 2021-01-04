package com.aliateck.fact.domaine.ports.api.edition;

import com.aliateck.fact.domaine.business.object.Facture;

public interface EditionApiService {
	
  public Facture editerFacture(String siret, long idPrestation, Facture facture);
  public Facture buildFacture(String siret, long idPrestation, Facture facture);
}

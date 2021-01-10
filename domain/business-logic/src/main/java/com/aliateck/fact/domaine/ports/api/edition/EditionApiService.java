package com.aliateck.fact.domaine.ports.api.edition;

import com.aliateck.fact.domaine.business.object.Facture;

public interface EditionApiService {
	
  public Facture editerFacture(String siret, Long idPrestation, Facture facture, String pathRoot);
  public byte[] downloadPdf(String siret, Long idPrestation, Long factureId, String rootDirectory);
}

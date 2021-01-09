package com.aliateck.fact.domaine.ports.spi.edition;

import com.aliateck.fact.domaine.business.object.Facture;

public interface EditionSpiService {
  public Facture buildFacture(String siret, long prestationId, Facture facture);

  public Facture editerFacture( String siret, long prestationId, Facture facture);
  
  public byte[] downloadPdf( String path);
}

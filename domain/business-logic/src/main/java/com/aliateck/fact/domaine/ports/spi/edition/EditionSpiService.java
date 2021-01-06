package com.aliateck.fact.domaine.ports.spi.edition;

import java.util.Map;

import com.aliateck.fact.domaine.business.object.Facture;

public interface EditionSpiService {
  public Facture buildFacture(String siret, long prestationId, Facture facture);

  public Map<String, Object> editerFacture( String siret, long prestationId, Facture facture);
}

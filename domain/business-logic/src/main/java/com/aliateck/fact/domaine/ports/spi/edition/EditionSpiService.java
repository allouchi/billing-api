package com.aliateck.fact.domaine.ports.spi.edition;

import com.aliateck.fact.domaine.business.object.Facture;
import java.util.Map;

public interface EditionSpiService {
  public Facture buildFacture(String siret, long prestationId, Facture facture);

  public Map<String, Object> editerFacture(
    String siret,
    long prestationId,
    Facture facture
  );
}

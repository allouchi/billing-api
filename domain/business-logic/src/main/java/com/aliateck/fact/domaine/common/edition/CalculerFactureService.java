package com.aliateck.fact.domaine.common.edition;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public interface CalculerFactureService {
  public Facture calculerFacture(Prestation prestation, Facture facture);
}

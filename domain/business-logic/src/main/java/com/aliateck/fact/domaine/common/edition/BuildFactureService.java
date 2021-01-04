package com.aliateck.fact.domaine.common.edition;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public interface BuildFactureService {
  public Facture calculerFacture(Prestation prestation, Facture facture);

  public Facture buildFacture(String siret, Prestation prestation, Facture facture);

  public String buildPathFile(String siret);
}

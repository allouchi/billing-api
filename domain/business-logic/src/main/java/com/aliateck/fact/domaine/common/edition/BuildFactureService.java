package com.aliateck.fact.domaine.common.edition;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public interface BuildFactureService {
  
  public Facture buildFacture(String siret, Prestation prestation, String moisFacture);

  public String buildPathFile(String siret, String pathRoot, String rsClient, String moisFacture,  Long moisFactureId);
}

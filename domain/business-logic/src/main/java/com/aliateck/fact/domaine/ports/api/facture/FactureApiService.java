package com.aliateck.fact.domaine.ports.api.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import java.util.List;

public interface FactureApiService {
  public void ajouterFacture(Facture facture);

  public void supprimerFacture(Facture facture);

  public void mettreAJourFacture(Facture facture);

  public Facture chercherFactureParId(long id);

  public Facture chercherFactureParNumero(String numero);

  public List<Facture> chercherFacturesByCompanyBySiret(String siret);
}

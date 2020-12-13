package com.aliateck.fact.domaine.ports.api.facture;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Facture;

public interface FactureApiService {
  public void ajouterFacture(Facture bill);

  public void supprimerFacture(Facture bill);

  public void mettreAJourFacture(Facture bill);

  public Facture chercherFactureParId(long id);

  public Facture chercherFactureParNumero(String numero);

  public List<Facture> chercherFacturesByCompanyBySiret(String siret);
}

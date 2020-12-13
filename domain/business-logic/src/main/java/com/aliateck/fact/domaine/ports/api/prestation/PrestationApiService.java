package com.aliateck.fact.domaine.ports.api.prestation;

import com.aliateck.fact.domaine.business.object.Prestation;
import java.util.List;

public interface PrestationApiService {
  public Prestation ajouterPrestation(Prestation prestation);

  public void supprimerPrestation(Prestation prestation);

  public Prestation mettreAJourPrestation(Prestation prestation);

  public List<Prestation> retournerPrestations();

  public Prestation chercherPrestationParId(long id);
}

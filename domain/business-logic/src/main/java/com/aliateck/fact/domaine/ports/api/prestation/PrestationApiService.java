package com.aliateck.fact.domaine.ports.api.prestation;

import com.aliateck.fact.domaine.business.object.Prestation;
import java.util.List;

public interface PrestationApiService {
  public Prestation addPrestation(Prestation prestation, String siret);

  public void deletePrestation(Prestation prestation);

  public Prestation updatePrestation(Prestation prestation, String siret);

  public List<Prestation> getAllPrestations();

  public Prestation findById(long id);
  
  public void deleteById(long id);
}

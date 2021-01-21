package com.aliateck.fact.domaine.ports.api.prestation;

import com.aliateck.fact.domaine.business.object.Prestation;
import java.util.List;

public interface PrestationApiService {
  public Prestation addPrestation(Prestation prestation, String siret); 

  public Prestation updatePrestation(Prestation prestation, String siret);

  public List<Prestation> findAll(String siret);

  public Prestation findById(long id);
  
  public void deletePrestation(long id);
}

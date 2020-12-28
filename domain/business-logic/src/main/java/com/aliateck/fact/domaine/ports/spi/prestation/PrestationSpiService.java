package com.aliateck.fact.domaine.ports.spi.prestation;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Prestation;

public interface PrestationSpiService {
  public Prestation addPrestation(Prestation prestation, String siret);

  public void deletePrestation(Prestation prestation);

  public Prestation updatePrestation(Prestation prestation);

  public Prestation findById(long id);

  public List<Prestation> findAll();
  
  public void deleteById(long id);
}

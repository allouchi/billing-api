package com.aliateck.fact.domaine.ports.spi.prestation;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Prestation;

public interface PrestationSpiService {
  public Prestation addPrestation(Prestation prestation, boolean templateChoice, String siret, Long moisPrestaId);

  public void deletePrestation(Prestation prestation);

  public Prestation updatePrestation(Prestation prestation, String siret);

  public Prestation findById(Long id);

  public List<Prestation> findAll(String siret);
  
  public void deleteById(Long id);
}

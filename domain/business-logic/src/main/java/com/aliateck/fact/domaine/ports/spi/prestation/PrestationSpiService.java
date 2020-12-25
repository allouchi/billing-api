package com.aliateck.fact.domaine.ports.spi.prestation;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Prestation;

public interface PrestationSpiService {
  Prestation addPrestation(Prestation prestation, String siret);

  void deletePrestation(Prestation prestation);

  Prestation updatePrestation(Prestation prestation);

  Prestation findById(long id);

  List<Prestation> findAll();
}

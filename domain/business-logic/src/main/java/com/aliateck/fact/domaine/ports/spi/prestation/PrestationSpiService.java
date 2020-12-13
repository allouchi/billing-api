package com.aliateck.fact.domaine.ports.spi.prestation;

import com.aliateck.fact.domaine.business.object.Prestation;
import java.util.List;

public interface PrestationSpiService {
  Prestation addPrestation(Prestation prestation);

  void deletePrestation(Prestation prestation);

  Prestation updatePrestation(Prestation prestation);

  Prestation findById(long id);

  List<Prestation> findAll();
}

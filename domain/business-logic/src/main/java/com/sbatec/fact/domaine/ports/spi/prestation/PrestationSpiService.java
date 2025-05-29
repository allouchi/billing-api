package com.sbatec.fact.domaine.ports.spi.prestation;

import com.sbatec.fact.domaine.business.object.Prestation;

import java.util.List;

public interface PrestationSpiService {
    Prestation addPrestation(Prestation prestation, String siret);

    void deletePrestation(Prestation prestation);

    Prestation updatePrestation(Prestation prestation);

    Prestation findById(Long id);

    List<Prestation> findAll(String siret);

    void deleteById(Long id);
}

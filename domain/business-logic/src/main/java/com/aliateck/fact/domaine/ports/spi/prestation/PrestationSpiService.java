package com.aliateck.fact.domaine.ports.spi.prestation;

import com.aliateck.fact.domaine.business.object.Prestation;

import java.util.List;

public interface PrestationSpiService {
    Prestation addPrestation(Prestation prestation, Boolean templateChoice, String siret, Long moisPrestaId);

    void deletePrestation(Prestation prestation);

    Prestation updatePrestation(Prestation prestation, String siret);

    Prestation findById(Long id);

    List<Prestation> findAll(String siret);

    void deleteById(Long id);
}

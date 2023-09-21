package com.aliateck.fact.domaine.ports.api.prestation;

import com.aliateck.fact.domaine.business.object.Prestation;

import java.util.List;

public interface PrestationApiService {
    Prestation addPrestation(Prestation prestation, String siret);

    Prestation updatePrestation(Prestation prestation, String siret);

    List<Prestation> findAll(String siret);

    Prestation findById(long id);

    void deletePrestation(long id);
}

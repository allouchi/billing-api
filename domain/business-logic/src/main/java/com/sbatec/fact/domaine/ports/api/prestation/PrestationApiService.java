package com.sbatec.fact.domaine.ports.api.prestation;

import com.sbatec.fact.domaine.business.object.Prestation;

import java.util.List;

public interface PrestationApiService {
    Prestation addPrestation(Prestation prestation, String siret);

    Prestation updatePrestation(Prestation prestation);

    List<Prestation> findAll(String siret);

    Prestation findById(long id);

    void deletePrestation(long id);
}

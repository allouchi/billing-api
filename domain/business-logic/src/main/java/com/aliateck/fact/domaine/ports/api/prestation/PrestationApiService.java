package com.aliateck.fact.domaine.ports.api.prestation;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Prestation;

public interface PrestationApiService {
	public Prestation addPrestation(Prestation prestation, boolean templateChoice, String siret, Long moisPrestaId);

	public Prestation updatePrestation(Prestation prestation, String siret);

	public List<Prestation> findAll(String siret);

	public Prestation findById(long id);

	public void deletePrestation(long id);
}

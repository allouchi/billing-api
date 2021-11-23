package com.aliateck.fact.domaine.ports.api.tva;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Tva;

public interface TvaApiService {

	public Tva addTva(Tva tva);

	public void deleteTva(Long id);

	public void updateTva(Tva tva);

	public Tva findByExercice(String exercice);

	public List<String> findAllExercises();

}

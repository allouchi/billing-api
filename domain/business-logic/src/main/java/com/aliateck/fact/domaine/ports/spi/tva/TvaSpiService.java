package com.aliateck.fact.domaine.ports.spi.tva;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Tva;

public interface TvaSpiService {

	public void delteByExercice(String exercice);

	public void delteById(Long id);

	public Tva findByExercice(String exercice);

	public void updateTva(Tva tva);

	public Tva addTva(Tva tva);

	public List<String> makeExercises();

}

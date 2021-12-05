package com.aliateck.fact.domaine.ports.spi.tva;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Tva;

public interface TvaSpiService {

	public void deleteByExercise(String exercise);

	public void deleteById(Long id);

	public List<Tva> findByExercise(String exercise);

	public Tva findById(Long id);

	public void updateTva(Tva tva);

	public Tva addTva(Tva tva);

	public List<Tva> findAllTva();

	public float findSumTva(String exercise);
}

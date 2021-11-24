package com.aliateck.fact.domaine.ports.spi.tva;

import com.aliateck.fact.domaine.business.object.Exercise;

public interface ExerciseSpiService {

	public void delteByExercise(String exercice);

	public void delteById(Long id);

	public Exercise findByExercise(String exercice);

	public void updateExercise(Exercise tva);

	public Exercise addExercise(Exercise tva);

}

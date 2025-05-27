package com.sbatec.fact.domaine.ports.spi.tva;

import java.util.List;

import com.sbatec.fact.domaine.business.object.Exercise;

/**
 * 
 * @author MALIANE
 *
 */
public interface ExerciseSpiService {

	public void delteByExercise(String exercice);

	public void delteById(Long id);

	public Exercise findByExercise(String exercice);

	public List<Exercise> findExercisesRef();

	public void updateExercise(Exercise tva);

	public Exercise addExercise(Exercise tva);

}

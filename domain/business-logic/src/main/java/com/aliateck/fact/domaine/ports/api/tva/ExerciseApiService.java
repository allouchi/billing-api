package com.aliateck.fact.domaine.ports.api.tva;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Exercise;

/**
 * 
 * @author MALIANE
 *
 */
public interface ExerciseApiService {

	public Exercise addExercise(Exercise exercise);

	public void deleteExercise(Long id);

	public void updateExercise(Exercise exercise);

	public Exercise findByExercise(String exercice);

	public List<String> findAllExercises();

}

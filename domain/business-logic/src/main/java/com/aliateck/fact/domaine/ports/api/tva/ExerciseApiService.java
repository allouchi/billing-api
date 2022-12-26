package com.aliateck.fact.domaine.ports.api.tva;

import com.aliateck.fact.domaine.business.object.Exercise;

import java.util.List;

/**
 * @author MALIANE
 */

public interface ExerciseApiService {

    List<Exercise> findExercisesRef();

}

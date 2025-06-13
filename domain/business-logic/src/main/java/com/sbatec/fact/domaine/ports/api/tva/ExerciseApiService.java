package com.sbatec.fact.domaine.ports.api.tva;

import com.sbatec.fact.domaine.business.object.Exercise;

import java.util.List;

/**
 * @author MALIANE
 */

public interface ExerciseApiService {

    List<Exercise> findExercisesRef();

}

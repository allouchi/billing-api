package com.aliateck.fact.domaine.adapter.tva;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Exercise;
import com.aliateck.fact.domaine.ports.api.tva.ExerciseApiService;
import com.aliateck.fact.domaine.ports.spi.tva.ExerciseSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExerciseApiAdapter implements ExerciseApiService {

	ExerciseSpiService exerciseSpiService;

	@Override
	public Exercise addExercise(Exercise exercise) {
		return exerciseSpiService.addExercise(exercise);
	}

	@Override
	public void deleteExercise(Long id) {
		

	}

	@Override
	public void updateExercise(Exercise exercise) {
		

	}

	@Override
	public Exercise findByExercise(String exercice) {
		return exerciseSpiService.findByExercise(exercice);
	}

	@Override
	public List<Exercise> findAllExercises() {
		return exerciseSpiService.findExercisesRef();
	}

}

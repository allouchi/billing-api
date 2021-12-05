package com.aliateck.fact.application.rest.controllers.tva;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Exercise;
import com.aliateck.fact.domaine.ports.api.tva.ExerciseApiService;
import com.aliateck.util.CommonResource.Resource;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(Resource.EXERCISES)
public class ExerciseController {

	private ExerciseApiService exerciseApiService;

	@GetMapping
	public ResponseEntity<List<Exercise>> findAllExercises() {
		log.info("find all exercises ref");
		return ResponseEntity.ok(exerciseApiService.findAllExercises());
	}

	@GetMapping(value = "/{exercice}")
	public Exercise getExercise(@PathVariable String exercice) {
		return exerciseApiService.findByExercise(exercice);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public void updateExercise(@RequestBody Exercise exercise) {
		exerciseApiService.updateExercise(exercise);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteExercise(@PathVariable Long id) {
		exerciseApiService.deleteExercise(id);
	}

	@PostMapping(value = "/{id}")
	public void addExercise(@PathVariable Exercise tvaRequest) {
		exerciseApiService.addExercise(tvaRequest);
	}

}

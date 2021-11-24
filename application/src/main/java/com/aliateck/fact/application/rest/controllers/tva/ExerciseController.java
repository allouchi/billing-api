package com.aliateck.fact.application.rest.controllers.tva;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.util.CommonResource.Resource;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(Resource.TVAS)
public class ExerciseController implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}
	/*
	 * private ExerciseApiService exerciseApiService;
	 * 
	 * @GetMapping(value = "/{exercice}") public Exercise getExercise(@PathVariable String exercice) { return
	 * exerciseApiService.findByExercice(exercice); }
	 * 
	 * @PutMapping(consumes = "application/json", produces = "application/json") public void updateExercise(@RequestBody
	 * Exercise exercise) { exerciseApiService.updateExercise(exercise); }
	 * 
	 * @DeleteMapping(value = "/{id}") public void deleteExercise(@PathVariable Long id) {
	 * exerciseApiService.deleteExercise(id); }
	 * 
	 * @PostMapping(value = "/{id}") public void addExercise(@PathVariable Exercise tvaRequest) {
	 * exerciseApiService.addExercise(tvaRequest); }
	 * 
	 * @GetMapping public ResponseEntity<List<String>> findAllExercises() { log.info("find all exercises"); return
	 * ResponseEntity.ok(exerciseApiService.findAllExercises()); }
	 * 
	 * @Override public void run(String... args) throws Exception { Exercise exercise =
	 * Exercise.builder().exercise("2021-2021").build(); exerciseApiService.addExercise(exercise);
	 * 
	 * }
	 */

}

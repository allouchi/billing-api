package com.aliateck.fact.application.rest.controllers.tva;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.business.object.TvaInfo;
import com.aliateck.fact.domaine.ports.api.tva.TvaApiService;
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
public class TvaController {

	private TvaApiService tvaApiService;
	//private ExerciseApiService exerciseApiService;

	// @GetMapping(value = "/{id}")
	// public Tva getTva(@PathVariable Long id) {
	// return tvaApiService.findById(id);
	// }

	@GetMapping(value = "/{exercise}")
	public List<Tva> getByExercise(@PathVariable String exercise) {
		log.info("---- Get All tvas by exercise : " + exercise);
		List<Tva> tvas = tvaApiService.findByExercise(exercise);

		return tvas;
	}
	
	@GetMapping(value = "/{exercise}/{exercise}")
	public TvaInfo getTvaInfoByExercise(@PathVariable String exercise) {
		log.info("---- Get All tvas info by exercise : " + exercise);
		TvaInfo tvas = tvaApiService.findTvaInfo(exercise);
		return tvas;
	}	
	
	 @PutMapping(consumes = "application/json", produces = "application/json")
	 public void updateTva(@RequestBody Tva tvaRequest) {
		 log.info("---- add or update tva : " + tvaRequest);
		 tvaApiService.updateTva(tvaRequest);
	 }
	
	@DeleteMapping(value = "/{id}")
	public void deleteTva(@PathVariable Long id) {
		log.info("---- delete by id : " + id);
		tvaApiService.deleteTva(id);
	}
	//
	@PostMapping(consumes = "application/json", produces = "application/json")
	public void addTva(@RequestBody Tva tvaRequest) {
		log.info("---- add or update tva : " + tvaRequest);
		tvaApiService.addTva(tvaRequest);
	}
	

}

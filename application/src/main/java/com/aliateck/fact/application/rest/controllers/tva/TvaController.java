package com.aliateck.fact.application.rest.controllers.tva;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Exercise;
import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.ports.api.tva.ExerciseApiService;
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
public class TvaController implements CommandLineRunner {

	private TvaApiService tvaApiService;
	private ExerciseApiService exerciseApiService;

	@GetMapping(value = "/{id}")
	public Tva getTva(@PathVariable Long id) {
		return tvaApiService.findById(id);
	}

	@GetMapping(value = "/{exercise}")
	public List<Tva> getAllTva(@PathVariable String exercise) {
		return tvaApiService.findByExercise(exercise);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public void updateTva(@RequestBody Tva tvaRequest) {
		tvaApiService.updateTva(tvaRequest);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteTva(@PathVariable Long id) {
		tvaApiService.deleteTva(id);
	}

	@PostMapping(value = "/{id}")
	public void addTva(@PathVariable Tva tvaRequest) {
		tvaApiService.addTva(tvaRequest);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Tva> tvas1 = new ArrayList<>();
		List<Tva> tvas2 = new ArrayList<>();
		Tva tva1 = Tva.builder().datePayment("01/01/2021").montantPayment(1000f).exercise("2020/2021").build();
		Tva tva2 = Tva.builder().datePayment("01/02/2021").montantPayment(2000f).exercise("2020/2021").build();
		Tva tva3 = Tva.builder().datePayment("01/03/2021").montantPayment(3000f).exercise("2021/2022").build();
		Tva tva4 = Tva.builder().datePayment("01/04/2021").montantPayment(4000f).exercise("2022/2023").build();

		tvas1.add(tva1);
		tvas1.add(tva2);
		tvas2.add(tva3);
		tvas2.add(tva4);

		Exercise e1 = Exercise.builder().exercise("2020/2021").build();
		Exercise e2 = Exercise.builder().exercise("2021/2022").build();
		Exercise e3 = Exercise.builder().exercise("2022/2023").build();

		exerciseApiService.addExercise(e1);
		// exerciseApiService.addExercise(e2);
		// exerciseApiService.addExercise(e3);

		tvaApiService.addTva(tva1);
		// tvaApiService.addTva(tva2);
		// tvaApiService.addTva(tva3);

	}

}

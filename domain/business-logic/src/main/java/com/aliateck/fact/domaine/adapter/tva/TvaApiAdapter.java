package com.aliateck.fact.domaine.adapter.tva;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.ports.api.tva.TvaApiService;
import com.aliateck.fact.domaine.ports.spi.tva.TvaSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TvaApiAdapter implements TvaApiService {

	TvaSpiService tvaSpiService;

	@Override
	public Tva addTva(Tva tva) {
		return tvaSpiService.addTva(tva);
	}

	@Override
	public void deleteTva(Long id) {
		tvaSpiService.deleteById(id);

	}

	@Override
	public void updateTva(Tva tva) {
		tvaSpiService.updateTva(tva);

	}

	@Override
	public List<Tva> findByExercise(String exercise) {
		return tvaSpiService.findByExercise(exercise);
	}

	@Override
	public Tva findById(Long id) {
		return tvaSpiService.findById(id);
	}

	@Override
	public List<Tva> findAllTva() {
		return tvaSpiService.findAllTva();
	}

	@Override
	public void deleteByExercise(String exercise) {
		tvaSpiService.deleteByExercise(exercise);
	}

}

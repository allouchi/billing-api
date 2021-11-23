package com.aliateck.fact.infrastructure.adapter.tva;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.ports.spi.tva.TvaSpiService;
import com.aliateck.fact.infrastructure.mapper.TvaMapper;
import com.aliateck.fact.infrastructure.models.TvaEntity;
import com.aliateck.fact.infrastructure.repository.tva.TvaJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TvaSpiAdapter implements TvaSpiService {

	TvaMapper tvaMapper;
	TvaJpaRepository tvaJpaRepository;

	@Override
	public Tva findByExercice(String exercice) {
		Optional<TvaEntity> entity = tvaJpaRepository.findByExercice(exercice);
		return tvaMapper.fromEntityToDomain(entity.get());
	}

	@Override
	public void delteByExercice(String exercice) {
		tvaJpaRepository.deleteByExercice(exercice);

	}

	@Override
	public void delteById(Long id) {
		tvaJpaRepository.deleteById(id);

	}

	@Override
	public Tva addTva(Tva tva) {
		TvaEntity entity = tvaJpaRepository.saveAndFlush(tvaMapper.fromDomainToEntity(tva));
		return tvaMapper.fromEntityToDomain(entity);

	}

	@Override
	public void updateTva(Tva tva) {
		Optional<TvaEntity> entity = tvaJpaRepository.findById(tva.getId());
		if (entity.isPresent()) {
			TvaEntity e = entity.get();
			e.setDatePayment(tva.getDatePayment());
			e.setExercice(tva.getExercice());
			tvaJpaRepository.saveAndFlush(e);

		}

	}

	@Override
	public List<String> makeExercises() {
		List<String> exercises = new ArrayList<>();
		return null;
	}

}

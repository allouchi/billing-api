package com.aliateck.fact.infrastructure.adapter.tva;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.ports.spi.tva.TvaSpiService;
import com.aliateck.fact.infrastructure.mapper.TvaMapper;
import com.aliateck.fact.infrastructure.models.ExerciseEntity;
import com.aliateck.fact.infrastructure.models.TvaEntity;
import com.aliateck.fact.infrastructure.repository.tva.ExerciseJpaRepository;
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

	private TvaMapper tvaMapper;
	private TvaJpaRepository tvaJpaRepository;
	private ExerciseJpaRepository exerciseJpaRepository;

	@Override
	public List<Tva> findByExercise(String exercice) {

		Optional<ExerciseEntity> e = exerciseJpaRepository.findByExercise(exercice);
		if (e.isPresent()) {

		}
		return Collections.emptyList();
	}

	@Override
	public void deleteByExercise(String exercise) {
		Optional<ExerciseEntity> e = exerciseJpaRepository.findByExercise(exercise);
		if (e.isPresent()) {

		}

	}

	@Override
	public void deleteById(Long id) {
		tvaJpaRepository.deleteById(id);

	}

	@Override
	public Tva addTva(Tva tva) {
		TvaEntity entity = tvaJpaRepository.save(tvaMapper.fromDomainToEntity(tva));
		return tvaMapper.fromEntityToDomain(entity);

	}

	@Override
	public void updateTva(Tva tva) {
		Optional<TvaEntity> entity = tvaJpaRepository.findById(tva.getId());
		if (entity.isPresent()) {
			TvaEntity e = entity.get();
			e.setDatePayment(tva.getDatePayment());
			e.setMontantPayment(tva.getMontantPayment());
			tvaJpaRepository.saveAndFlush(e);
		}
	}

	@Override
	public Tva findById(Long id) {
		Optional<TvaEntity> o = tvaJpaRepository.findById(id);
		if (o.isPresent()) {
			return tvaMapper.fromEntityToDomain(o.get());
		}
		return null;

	}

	@Override
	public List<Tva> findAllTva() {
		List<TvaEntity> entities = tvaJpaRepository.findAll();
		return tvaMapper.fromEntityToDomain(entities);
	}

}

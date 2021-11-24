package com.aliateck.fact.infrastructure.adapter.tva;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Exercise;
import com.aliateck.fact.domaine.ports.spi.tva.ExerciseSpiService;
import com.aliateck.fact.infrastructure.mapper.ExerciseMapper;
import com.aliateck.fact.infrastructure.models.ExerciseEntity;
import com.aliateck.fact.infrastructure.repository.tva.ExerciseJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExerciseSpiAdapter implements ExerciseSpiService {

	ExerciseMapper exerciseMapper;
	ExerciseJpaRepository exerciseJpaRepository;

	@Override
	public void delteByExercise(String exercice) {

	}

	@Override
	public void delteById(Long id) {

	}

	@Override
	public void updateExercise(Exercise exercise) {

	}

	@Override
	public Exercise findByExercise(String exercice) {
		Optional<ExerciseEntity> o = exerciseJpaRepository.findByExercise(exercice);
		if (o.isPresent()) {
			return exerciseMapper.fromEntityToDomain(o.get());
		}
		return null;
	}

	@Override
	public Exercise addExercise(Exercise exercise) {
		ExerciseEntity entity = exerciseJpaRepository.save(exerciseMapper.fromDomainToEntity(exercise));
		return exerciseMapper.fromEntityToDomain(entity);

	}

}

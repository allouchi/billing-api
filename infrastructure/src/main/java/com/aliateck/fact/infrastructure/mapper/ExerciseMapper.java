package com.aliateck.fact.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.aliateck.fact.domaine.business.object.Exercise;
import com.aliateck.fact.infrastructure.models.ExerciseEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExerciseMapper {

	public ExerciseEntity fromDomainToEntity(Exercise domain) {
		if (domain == null) {
			return null;
		}

		return ExerciseEntity.builder().id(domain.getId()).exercise(domain.getExercise()).build();
	}

	public Exercise fromEntityToDomain(ExerciseEntity entity) {

		if (entity == null) {
			return null;
		}

		return Exercise.builder().id(entity.getId()).exercise(entity.getExercise()).build();
	}

}

package com.sbatec.fact.infrastructure.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sbatec.fact.domaine.business.object.Exercise;
import com.sbatec.fact.infrastructure.models.ExerciseEntity;

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

	public List<Exercise> fromEntityToDomain(List<ExerciseEntity> entities) {

		if (entities != null) {
			return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<ExerciseEntity> fromDomainToEntity(List<Exercise> domains) {
		if (domains != null) {
			return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
		}
		return Collections.emptyList();

	}

}

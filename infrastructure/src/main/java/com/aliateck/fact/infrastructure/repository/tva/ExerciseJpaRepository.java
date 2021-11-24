package com.aliateck.fact.infrastructure.repository.tva;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliateck.fact.infrastructure.models.ExerciseEntity;

@Repository
public interface ExerciseJpaRepository extends JpaRepository<ExerciseEntity, Long> {

	public Optional<ExerciseEntity> findByExercise(String exercise);

	public void deleteByExercise(String exercise);

}

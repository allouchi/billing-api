package com.sbatec.fact.infrastructure.repository.tva;

import com.sbatec.fact.infrastructure.models.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseJpaRepository extends JpaRepository<ExerciseEntity, Long> {

    List<ExerciseEntity> findByExercise(String exercise);

    void deleteByExercise(String exercise);

}

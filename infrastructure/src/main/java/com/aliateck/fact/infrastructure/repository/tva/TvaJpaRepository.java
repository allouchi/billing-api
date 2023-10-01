package com.aliateck.fact.infrastructure.repository.tva;

import com.aliateck.fact.infrastructure.models.TvaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvaJpaRepository extends JpaRepository<TvaEntity, Long> {

    public List<TvaEntity> findByExercise(String exercise);

    public List<TvaEntity> findByExerciseAndSiret(String exercise, String siret);

    public List<TvaEntity> findBySiret(String siret);

}

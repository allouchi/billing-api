package com.aliateck.fact.infrastructure.repository.tva;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliateck.fact.infrastructure.models.TvaEntity;

@Repository
public interface TvaJpaRepository extends JpaRepository<TvaEntity, Long> {

	public List<TvaEntity> findByExercise(String exercise);

}

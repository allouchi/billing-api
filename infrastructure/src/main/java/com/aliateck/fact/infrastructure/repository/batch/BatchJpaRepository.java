package com.aliateck.fact.infrastructure.repository.batch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliateck.fact.infrastructure.models.FactureEntity;

@Repository
public interface BatchJpaRepository extends JpaRepository<FactureEntity, Long> {

}

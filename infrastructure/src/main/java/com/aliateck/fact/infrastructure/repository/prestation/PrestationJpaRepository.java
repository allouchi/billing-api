package com.aliateck.fact.infrastructure.repository.prestation;

import com.aliateck.fact.infrastructure.models.PrestationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestationJpaRepository extends JpaRepository<PrestationEntity, Long> {}

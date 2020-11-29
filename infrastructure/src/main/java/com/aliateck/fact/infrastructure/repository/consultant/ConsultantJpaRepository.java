package com.aliateck.fact.infrastructure.repository.consultant;

import com.aliateck.fact.infrastructure.models.ConsultantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultantJpaRepository extends JpaRepository<ConsultantEntity, Long> {}

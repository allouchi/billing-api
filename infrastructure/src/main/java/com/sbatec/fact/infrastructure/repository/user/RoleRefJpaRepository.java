package com.sbatec.fact.infrastructure.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbatec.fact.infrastructure.models.RoleRefEntity;

@Repository
public interface RoleRefJpaRepository extends JpaRepository<RoleRefEntity, Long> {
  
}

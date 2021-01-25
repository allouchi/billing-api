package com.aliateck.fact.infrastructure.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliateck.fact.infrastructure.models.UserRoleRefEntity;

@Repository
public interface UserRoleRefJpaRepository extends JpaRepository<UserRoleRefEntity, Long> {
  
}

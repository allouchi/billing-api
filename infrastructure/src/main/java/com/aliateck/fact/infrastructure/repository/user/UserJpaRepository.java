package com.aliateck.fact.infrastructure.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliateck.fact.infrastructure.models.UserEntity;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByUserName(String userName); 
  
}

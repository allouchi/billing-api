package com.aliateck.fact.infrastructure.repository.consultant;

import com.aliateck.fact.infrastructure.models.ConsultantEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultantJpaRepository extends JpaRepository<ConsultantEntity, Long> {
  public Optional<ConsultantEntity> findByEmail(String mail);

  public Optional<ConsultantEntity>  findByFirstName(String firstName);

  public Optional<ConsultantEntity>  findByLastName(String lastName);
}

package com.aliateck.fact.infrastructure.repository.company;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliateck.fact.infrastructure.models.CompanyEntity;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Long> {
  Optional<CompanyEntity> findBySocialReasonIgnoreCase(String reasonSocial);

  Optional<CompanyEntity> findBySiret(String siret);

}

package com.aliateck.fact.infrastructure.repository.company;

import com.aliateck.fact.infrastructure.models.CompanyEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Long> {
  CompanyEntity findBySocialReasonIgnoreCase(String reasonSocial);

  CompanyEntity findBySiret(String siret);

  Optional<CompanyEntity> findByUsers_id(Long id);
}

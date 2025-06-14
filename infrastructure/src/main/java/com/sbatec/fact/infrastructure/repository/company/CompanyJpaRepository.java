package com.sbatec.fact.infrastructure.repository.company;

import com.sbatec.fact.infrastructure.models.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Long> {

    Optional<CompanyEntity> findBySiret(String siret);


}

package com.sbatec.fact.infrastructure.repository.facture;

import com.sbatec.fact.infrastructure.models.FactureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureJpaRepository extends JpaRepository<FactureEntity, Long> {

    FactureEntity getByNumeroFacture(String numeroFacture);

    List<FactureEntity> findByDateEncaissementIsNull();

}

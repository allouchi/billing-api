package com.aliateck.fact.infrastructure.repository.facture;

import com.aliateck.fact.infrastructure.models.FactureEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureJpaRepository extends JpaRepository<FactureEntity, Long> {
 

  public FactureEntity getByNumeroFacture(String numeroFacture);

  public List<FactureEntity> findByStatus(String status);

  public List<FactureEntity> findByDateEcheance(Date dateEcheance);

  public List<FactureEntity> findByDateEncaissement(Date dateEncaissement);
}

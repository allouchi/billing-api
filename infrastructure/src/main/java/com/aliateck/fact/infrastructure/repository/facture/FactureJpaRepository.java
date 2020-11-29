package com.aliateck.fact.infrastructure.repository.facture;

import com.aliateck.fact.infrastructure.models.FactureEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureJpaRepository extends JpaRepository<FactureEntity, Long> {
  FactureEntity getFactureByNumeroFacture(String numero);

  List<FactureEntity> getFactureByStatus(String status);

  FactureEntity getFactureByDateEcheance(Date dateEcheance);

  List<FactureEntity> getFactureByDateEncaissement(Date dateEncaissement);
  //List<FactureEntity> getFactureByClient(long numero);

}

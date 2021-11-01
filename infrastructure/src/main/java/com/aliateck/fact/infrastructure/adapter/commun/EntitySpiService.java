package com.aliateck.fact.infrastructure.adapter.commun;

import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import java.util.List;

public interface EntitySpiService {
  List<FactureEntity> findFacturesByPrestation(String siret, long idPrestation);
  List<FactureEntity> findAllFacturesBySiret(String siret);
  List<FactureEntity> findAllFactures();
  PrestationEntity findPrestationById(String siret, Long prestationId);
  List<PrestationEntity> findAllPrestation(String siret);
  FactureEntity findFactureById(Long factureId);
}

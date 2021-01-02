package com.aliateck.fact.infrastructure.adapter.commun;

import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import java.util.List;

public interface CommonSpiEntityService {
  List<FactureEntity> findFacturesByPrestation(String siret, long idPrestation);
  List<FactureEntity> findAllFacturesBySiret(String siret);
  PrestationEntity findPrestationById(String siret, long prestationId);
  List<PrestationEntity> findAllPrestation(String siret);
  FactureEntity findFactureById(String siret, long prestationId, long factureId);
}

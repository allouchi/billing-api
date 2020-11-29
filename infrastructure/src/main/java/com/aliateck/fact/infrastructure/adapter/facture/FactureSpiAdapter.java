package com.aliateck.fact.infrastructure.adapter.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureSpiAdapter implements FactureSpiService {
  FactureJpaRepository factureJpaRepository;
  FactureMapper factureMapper;

  @Override
  public Facture getFactureByNumero(String numero) {
    FactureEntity entity = factureJpaRepository.getFactureByNumeroFacture(numero);
    return factureMapper.fromEntityToDomain(entity);
  }

  @Override
  public List<Facture> getFactureByStatus(String status) {
    List<FactureEntity> entitys = factureJpaRepository.getFactureByStatus(status);
    return factureMapper.fromEntityListToDomain(entitys);
  }

  @Override
  public Facture getFactureByDateEcheance(Date dateEcheance) {
    FactureEntity entity = factureJpaRepository.getFactureByDateEcheance(dateEcheance);
    return factureMapper.fromEntityToDomain(entity);
  }

  @Override
  public List<Facture> getFactureByDateEncaissement(Date dateEncaissement) {
    List<FactureEntity> entitys = factureJpaRepository.getFactureByDateEncaissement(
      dateEncaissement
    );
    return factureMapper.fromEntityListToDomain(entitys);
  }

  @Override
  public List<Facture> getFactureByNumero(long numero) {
    //FactureEntity > entitys = factureJpaRepository.getFactureByNumeroFacture(numero);
    return null; //factureMapper.fromEntityListToDomain(entitys);
  }
}

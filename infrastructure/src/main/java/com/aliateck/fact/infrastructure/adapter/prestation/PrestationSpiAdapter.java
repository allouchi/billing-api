package com.aliateck.fact.infrastructure.adapter.prestation;

import com.aliateck.fact.common.facture.UtilFacture;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.spi.prestation.PrestationSpiService;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.prestation.PrestationJpaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationSpiAdapter implements PrestationSpiService {
  PrestationJpaRepository prestationJpaRepository;
  PrestationMapper prestationMapper;

  @Override
  public Prestation addPrestation(Prestation prestation) {
    Facture facture = UtilFacture.calculerFacture(prestation);
    prestation.setFacture(facture);
    PrestationEntity entity = prestationMapper.fromDomainToEntity(prestation);
    PrestationEntity entityRet = prestationJpaRepository.save(entity);
    return prestationMapper.fromEntityToDomain(entityRet);
  }

  @Override
  public void deletePrestation(Prestation prestation) {
    PrestationEntity entity = prestationMapper.fromDomainToEntity(prestation);
    prestationJpaRepository.delete(entity);
  }

  @Override
  public Prestation updatePrestation(Prestation prestation) {
    Optional<PrestationEntity> objBase = prestationJpaRepository.findById(
      prestation.getId()
    );

    if (objBase.isPresent()) {
      PrestationEntity entityBase = objBase.get();
      entityBase.setId(prestation.getId());
      entityBase.setNumeroCommande(prestation.getNumeroCommande());
      entityBase.setDelaiPaiement(prestation.getDelaiPaiement());
      entityBase.setTarifHT(prestation.getTarifHT());
      PrestationEntity entityRet = prestationJpaRepository.save(entityBase);
      return prestationMapper.fromEntityToDomain(entityRet);
    }
    return null;
  }

  @Override
  public Prestation findById(long id) {
    Optional<PrestationEntity> entity = prestationJpaRepository.findById(id);
    if (entity.isPresent()) {
      prestationMapper.fromEntityToDomain(entity.get());
    }

    return null;
  }

  @Override
  public List<Prestation> findAll() {
    List<PrestationEntity> entities = prestationJpaRepository.findAll();
    return prestationMapper.fromEntityToDomain(entities);
  }
}

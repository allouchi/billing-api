package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationMapper {
	
	
  private final FactureMapper factureMapper;
  

  public PrestationEntity fromDomainToEntity(Prestation domain) {
    return PrestationEntity
      .builder()
      .id(domain.getId())
      .nbJoursEffectue(domain.getNbJoursEffectue())
      .tarif(domain.getTarif())
      .facture(factureMapper.fromDomainToEntity(domain.getFacture()))      
      .build();
  }

  public Prestation fromEntityToDomain(PrestationEntity entity) {
    return Prestation
      .builder()
      .id(entity.getId())
      .nbJoursEffectue(entity.getNbJoursEffectue())
      .tarif(entity.getTarif())
      .facture(factureMapper.fromEntityToDomain(entity.getFacture()))
      .build();
  }
}

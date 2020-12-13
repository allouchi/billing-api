package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.infrastructure.models.AdresseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdresseMapper {

  public AdresseEntity fromDomainToEntity(Adresse domain) {
    return AdresseEntity
      .builder()
      .complementAdresse(domain.getComplementAdresse())
      .numero(domain.getNumero())
      .voie(domain.getVoie())
      .codePostal(domain.getCodePostal())
      .commune(domain.getCommune())
      .pays(domain.getPays())
      .build();
  }

  public Adresse fromEntityToDomain(AdresseEntity entity) {
    return Adresse
      .builder()
      .complementAdresse(entity.getComplementAdresse())
      .numero(entity.getNumero())
      .voie(entity.getVoie())
      .codePostal(entity.getCodePostal())
      .commune(entity.getCommune())
      .pays(entity.getPays())
      .build();
  }
}

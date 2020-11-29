package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.ClientAdresse;
import com.aliateck.fact.infrastructure.models.ClientAdresseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientAdresseMapper {

  public ClientAdresseEntity fromDomainToEntity(ClientAdresse domain) {
    return ClientAdresseEntity
      .builder()
      .numero(domain.getNumero())
      .voie(domain.getVoie())
      .codePostal(domain.getCodePostal())
      .commune(domain.getCommune())
      .pays(domain.getPays())
      .build();
  }

  public ClientAdresse fromEntityToDomain(ClientAdresseEntity entity) {
    return ClientAdresse
      .builder()
      .numero(entity.getNumero())
      .voie(entity.getVoie())
      .codePostal(entity.getCodePostal())
      .commune(entity.getCommune())
      .pays(entity.getPays())
      .build();
  }
}

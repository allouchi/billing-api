package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.CompanyAdresse;
import com.aliateck.fact.infrastructure.models.CompanyAdresseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyAdresseMapper {

  public CompanyAdresseEntity fromDomainToEntity(CompanyAdresse domain) {
    return CompanyAdresseEntity
      .builder()
      .numero(domain.getNumero())
      .voie(domain.getVoie())
      .codePostal(domain.getCodePostal())
      .commune(domain.getCommune())
      .pays(domain.getPays())
      .build();
  }

  public CompanyAdresse fromEntityToDomain(CompanyAdresseEntity entity) {
    return CompanyAdresse
      .builder()
      .numero(entity.getNumero())
      .voie(entity.getVoie())
      .codePostal(entity.getCodePostal())
      .commune(entity.getCommune())
      .pays(entity.getPays())
      .build();
  }
}

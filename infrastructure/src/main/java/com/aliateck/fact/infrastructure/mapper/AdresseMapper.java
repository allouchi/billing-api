package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.infrastructure.models.AdresseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdresseMapper {

  public AdresseEntity fromDomainToEntity(Adresse domain) {
	  if(domain == null) {
		  return null;
	  }
    return AdresseEntity
      .builder()      
      .numero(domain.getNumero())
      .voie(domain.getVoie())
      .complementAdresse(domain.getComplementAdresse())
      .codePostal(domain.getCodePostal())
      .commune(domain.getCommune())
      .pays(domain.getPays())
      .build();
  }

  public Adresse fromEntityToDomain(AdresseEntity entity) {
	  if(entity == null) {
		  return null;
	  }
    return Adresse
      .builder()      
      .numero(entity.getNumero())
      .voie(entity.getVoie())
      .complementAdresse(entity.getComplementAdresse())
      .codePostal(entity.getCodePostal())
      .commune(entity.getCommune())
      .pays(entity.getPays())
      .build();
  }
}

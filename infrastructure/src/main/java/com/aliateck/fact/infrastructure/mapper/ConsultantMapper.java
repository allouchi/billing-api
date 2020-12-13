package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.infrastructure.models.ConsultantEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultantMapper {

  public ConsultantEntity fromDomainToEntity(Consultant domain) {
    return ConsultantEntity
      .builder()
      .id(domain.getId())
      .firstName(domain.getFirstName())
      .lastName(domain.getLastName())
      .mail(domain.getMail())
      .build();
  }

  public Consultant fromEntityToDomain(ConsultantEntity entity) {
    return Consultant
      .builder()
      .id(entity.getId())
      .firstName(entity.getFirstName())
      .lastName(entity.getLastName())
      .mail(entity.getMail())
      .build();
  }

  public List<Consultant> fromEntityToDomain(List<ConsultantEntity> entities) {
    return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
  }

  public List<ConsultantEntity> fromDomainToEntity(List<Consultant> domains) {
    return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
  }
}

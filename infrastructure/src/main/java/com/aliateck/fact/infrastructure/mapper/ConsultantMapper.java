package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.infrastructure.models.ConsultantEntity;
import java.util.ArrayList;
import java.util.List;
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
    List<Consultant> consultantList = new ArrayList<>();

    for (ConsultantEntity entity : entities) {
      consultantList.add(fromEntityToDomain(entity));
    }
    return consultantList;
  }

  public List<ConsultantEntity> fromDomainToEntity(List<Consultant> domain) {
    List<ConsultantEntity> consultantList = new ArrayList<>();

    for (Consultant dom : domain) {
      consultantList.add(fromDomainToEntity(dom));
    }
    return consultantList;
  }
}

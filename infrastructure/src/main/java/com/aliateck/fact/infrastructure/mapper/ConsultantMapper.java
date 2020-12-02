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
  //private final ClientMapper clientMapper;
  private final PrestationMapper prestationMapper;

  public ConsultantEntity fromDomainToEntity(Consultant domain) {
    return ConsultantEntity
      .builder()
      .id(domain.getId())
      .firstName(domain.getFirstName())
      .lastName(domain.getLastName())
      .prestation(prestationMapper.fromDomainToEntity(domain.getPrestation()))
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
      .prestation(prestationMapper.fromEntityToDomain(entity.getPrestation()))
      .build();
  }

  public List<Consultant> fromEntityToDomain(List<ConsultantEntity> entities) {
    List<Consultant> consultantList = new ArrayList<>();

    if (entities != null && !entities.isEmpty()) {
      for (ConsultantEntity entity : entities) {
        consultantList.add(fromEntityToDomain(entity));
      }
    }

    return consultantList;
  }

  public List<ConsultantEntity> fromDomainToEntity(List<Consultant> domain) {
    List<ConsultantEntity> consultantList = new ArrayList<>();

    if (domain != null && !domain.isEmpty()) {
      for (Consultant dom : domain) {
        consultantList.add(fromDomainToEntity(dom));
      }
    }

    return consultantList;
  }
}

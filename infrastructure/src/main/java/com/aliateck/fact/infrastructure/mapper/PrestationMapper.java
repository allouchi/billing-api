package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationMapper {
  ClientMapper clientMapper;
  ConsultantMapper consultantMapper;


  public PrestationEntity fromDomainToEntity(Prestation domain) {
    if (domain == null) {
      return null;
    }
    
    return PrestationEntity
      .builder()
      .id(domain.getId())
      .client(clientMapper.fromDomainToEntity(domain.getClient()))
      .consultant(consultantMapper.fromDomainToEntity(domain.getConsultant()))
      .delaiPaiement(domain.getDelaiPaiement())
      .tarifHT(domain.getTarifHT())
      .numeroCommande(domain.getNumeroCommande())
      .build();
  }

  public Prestation fromEntityToDomain(PrestationEntity entity) {
    if (entity == null) {
      return null;
    }
    return Prestation
      .builder()
      .id(entity.getId())      
      .client(clientMapper.fromEntityToDomain(entity.getClient()))
      .consultant(consultantMapper.fromEntityToDomain(entity.getConsultant()))
      .delaiPaiement(entity.getDelaiPaiement())
      .tarifHT(entity.getTarifHT()) 
      .numeroCommande(entity.getNumeroCommande())
      .build();
  }

  public List<Prestation> fromEntityToDomain(List<PrestationEntity> entities) {
    return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
  }

  public List<PrestationEntity> fromDomainToEntity(List<Prestation> domains) {
    return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
  }
}

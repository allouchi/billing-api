package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.infrastructure.mapper.common.Mapper;
import com.aliateck.fact.infrastructure.models.ClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapper implements Mapper<Client, ClientEntity> {
  private final AdresseMapper adresseMapper;

  @Override
  public ClientEntity fromDomainToEntity(Client domain) {
    if (domain == null) {
      return null;
    }
    return ClientEntity
      .builder()
      .id(domain.getId())
      .socialReason(domain.getSocialReason())
      .adresseClient(adresseMapper.fromDomainToEntity(domain.getAdresseClient()))
      .build();
  }

  @Override
  public Client fromEntityToDomain(ClientEntity entity) {
    if (entity == null) {
      return null;
    }
    return Client
      .builder()
      .id(entity.getId())
      .socialReason(entity.getSocialReason())
      .adresseClient(adresseMapper.fromEntityToDomain(entity.getAdresseClient()))
      .build();
  }
}

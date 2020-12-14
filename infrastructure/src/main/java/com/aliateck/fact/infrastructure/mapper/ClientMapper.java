package com.aliateck.fact.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.infrastructure.mapper.common.Mapper;
import com.aliateck.fact.infrastructure.models.ClientEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientMapper implements Mapper<Client, ClientEntity> {
  private final AdresseMapper adresseMapper;

  @Override
  public ClientEntity fromDomainToEntity(Client domain) {
    return ClientEntity
      .builder()
      .id(domain.getId())
      .socialReason(domain.getSocialReason())
      .adresse(adresseMapper.fromDomainToEntity(domain.getAdresse()))
      .build();
  }

  @Override
  public Client fromEntityToDomain(ClientEntity entity) {
    return Client
      .builder()
      .id(entity.getId())
      .socialReason(entity.getSocialReason())
      .adresse(adresseMapper.fromEntityToDomain(entity.getAdresse()))
      .build();
  }
}

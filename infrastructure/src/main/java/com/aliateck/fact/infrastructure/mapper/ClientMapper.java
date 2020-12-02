package com.aliateck.fact.infrastructure.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.infrastructure.models.ClientEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientMapper {
  private final ClientAdresseMapper adresseMapper;

  public ClientEntity fromDomainToEntity(Client domain) {
    return ClientEntity
      .builder()
      .id(domain.getId())
      .socialReason(domain.getSocialReason())
      .adresse(adresseMapper.fromDomainToEntity(domain.getAdresse()))
      .build();
  }

  public Client fromEntityToDomain(ClientEntity entity) {
    return Client
      .builder()
      .id(entity.getId())
      .socialReason(entity.getSocialReason())
      .adresse(adresseMapper.fromEntityToDomain(entity.getAdresse()))
      .build();
  }

  public List<Client> fromEntityToDomain(List<ClientEntity> entities) {
    List<Client> clientList = new ArrayList<>();

    if (entities != null && !entities.isEmpty()) {
      for (ClientEntity entity : entities) {
        clientList.add(fromEntityToDomain(entity));
      }
    }
    return clientList;
  }

  public List<ClientEntity> fromDomainToEntityList(List<Client> domain) {
    List<ClientEntity> clientList = new ArrayList<>();

    if (domain != null && !domain.isEmpty()) {
      for (Client entity : domain) {
        clientList.add(fromDomainToEntity(entity));
      }
    }

    return clientList;
  }
}

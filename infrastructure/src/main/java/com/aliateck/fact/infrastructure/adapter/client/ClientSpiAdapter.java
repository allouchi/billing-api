package com.aliateck.fact.infrastructure.adapter.client;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.ports.spi.client.ClientSpiService;
import com.aliateck.fact.infrastructure.mapper.ClientMapper;
import com.aliateck.fact.infrastructure.models.ClientEntity;
import com.aliateck.fact.infrastructure.repository.client.ClientJpaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientSpiAdapter implements ClientSpiService {
  ClientJpaRepository clientJpaRepository;
  ClientMapper clientMapper;

  @Override
  public Client addClient(Client client) {
    ClientEntity entity = clientMapper.fromDomainToEntity(client);
    ClientEntity domain = clientJpaRepository.save(entity);
    return clientMapper.fromEntityToDomain(domain);
  }

  @Override
  public void removeClient(Client client) {
    ClientEntity entity = clientMapper.fromDomainToEntity(client);
    clientJpaRepository.delete(entity);
  }

  @Override
  public Client updateClient(Client client) {
    ClientEntity entity = clientMapper.fromDomainToEntity(client);
    Optional<ClientEntity> objBase = clientJpaRepository.findById(client.getId());
    if (objBase.isPresent()) {
      ClientEntity entityBase = objBase.get();
      entityBase.setId(entity.getId());
      entityBase.setSocialReason(entity.getSocialReason());
      //entityBase.setAdresse(entity.getAdresse());
      ClientEntity domain = clientJpaRepository.save(entityBase);
      return clientMapper.fromEntityToDomain(domain);
    }
    return null;
  }

  @Override
  public List<Client> findAllClients() {
    List<ClientEntity> clientsEntity = clientJpaRepository.findAll();
    return clientMapper.fromEntityToDomain(clientsEntity);
  }

  @Override
  public Client findClientById(long id) {
    Client client = null;
    Optional<ClientEntity> entity = clientJpaRepository.findById(id);
    if (entity.isPresent()) {
      client = clientMapper.fromEntityToDomain(entity.get());
    }
    return client;
  }
}

package com.aliateck.fact.infrastructure.adapter.client;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.ports.spi.client.ClientSpiService;
import com.aliateck.fact.infrastructure.mapper.ClientMapper;
import com.aliateck.fact.infrastructure.models.ClientEntity;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.repository.client.ClientJpaRepository;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
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
  CompanyJpaRepository companyJpaRepository;
  ClientMapper clientMapper;

  @Override
  public Client addClient(Client client, String siret) {
    Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
    return oCompany
      .map(
        company -> {
          List<ClientEntity> clients = company.getClients();
          ClientEntity entity = clientMapper.fromDomainToEntity(client);
          clients.add(entity);
          company.setClients(clients);
          companyJpaRepository.save(company);
          ClientEntity savedClient = company
            .getClients()
            .stream()
            .filter(c -> c.getSocialReason().equalsIgnoreCase(client.getSocialReason()))
            .findFirst()
            .orElseGet(null);
          return clientMapper.fromEntityToDomain(savedClient);
        }
      )
      .orElse(null);
  }

  @Override
  public void deleteClient(long id) {
    clientJpaRepository.deleteById(id);
  }

  @Override
  public Client updateClient(Client client, String siret) {
    Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
    if (oCompnay.isPresent()) {
      CompanyEntity entity = oCompnay.get();
      List<ClientEntity> oClient = entity.getClients();
      for (ClientEntity c : oClient) {
        if (c.getId().longValue() == client.getId().longValue()) {
          ClientEntity nEntity = clientMapper.fromDomainToEntity(client);
          ClientEntity domain = clientJpaRepository.save(nEntity);
          return clientMapper.fromEntityToDomain(domain);
        }
      }
    }
    return null;
  }

  @Override
  public List<Client> findAll() {
    List<ClientEntity> clientsEntity = clientJpaRepository.findAll();
    return clientMapper.fromEntityToDomain(clientsEntity);
  }

  @Override
  public Client findById(long id) {
    Client client = null;
    Optional<ClientEntity> entity = clientJpaRepository.findById(id);
    if (entity.isPresent()) {
      client = clientMapper.fromEntityToDomain(entity.get());
    }
    return client;
  }

}

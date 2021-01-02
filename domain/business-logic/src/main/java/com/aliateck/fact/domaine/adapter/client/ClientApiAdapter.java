package com.aliateck.fact.domaine.adapter.client;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import com.aliateck.fact.domaine.ports.spi.client.ClientSpiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientApiAdapter implements ClientApiService {
  ClientSpiService clientSpiService;

  @Override
  public Client addClient(Client client, String siret) {
    return clientSpiService.addClient(client, siret);
  }  

  @Override
  public Client updateClient(Client client, String siret) {
    return clientSpiService.updateClient(client, siret);
  }

  @Override
  public List<Client> findAllClients(String siret) {
    return clientSpiService.findAll(siret);
  }

  @Override
  public Client findById(long id) {
    return clientSpiService.findById(id);
  }

  @Override
  public void deleteById(long id) {
	  clientSpiService.deleteClient(id);
  }

}

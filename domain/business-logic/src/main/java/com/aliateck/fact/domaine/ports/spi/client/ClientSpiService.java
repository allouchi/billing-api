package com.aliateck.fact.domaine.ports.spi.client;

import com.aliateck.fact.domaine.business.object.Client;
import java.util.List;

public interface ClientSpiService {
  public Client addClient(Client client, String siret);

  public void removeClient(Client client);

  public Client updateClient(Client client);

  public List<Client> findAllClients();

  public Client findClientById(long id);
}

package com.aliateck.fact.domaine.ports.api.client;

import com.aliateck.fact.domaine.business.object.Client;
import java.util.List;

public interface ClientApiService {
  public Client addClient(Client client);

  public void deleteClient(Client client);

  public Client updateClient(Client client);

  public List<Client> findAllClients();

  public Client findById(long id);
}

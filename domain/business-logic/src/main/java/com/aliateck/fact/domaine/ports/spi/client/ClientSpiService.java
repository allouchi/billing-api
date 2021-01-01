package com.aliateck.fact.domaine.ports.spi.client;

import com.aliateck.fact.domaine.business.object.Client;
import java.util.List;

public interface ClientSpiService {
  public Client addClient(Client client, String siret);

  public void deleteClient(long id);

  public Client updateClient(Client client, String siret);

  public List<Client> findAll();

  public Client findById(long id);
}

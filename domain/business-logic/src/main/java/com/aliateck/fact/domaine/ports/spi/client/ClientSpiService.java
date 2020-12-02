package com.aliateck.fact.domaine.ports.spi.client;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Client;

public interface ClientSpiService {
	
	public void addClient(Client client) ;

	public void removeClient(Client client);

	public void updateClient(Client client);

	public List<Client> findAllClients();

	public Client findClientById(long id);
	
	
}

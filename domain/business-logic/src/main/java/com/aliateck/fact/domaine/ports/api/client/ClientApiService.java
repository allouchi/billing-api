package com.aliateck.fact.domaine.ports.api.client;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Client;


public interface ClientApiService {
	
	public void addClient(Client client) ;

	public void deleteClient(Client client);

	public void updateClient(Client client);

	public List<Client> findAllClients();

	public Client findById(long id);	

}

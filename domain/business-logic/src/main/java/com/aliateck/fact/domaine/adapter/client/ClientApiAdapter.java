package com.aliateck.fact.domaine.adapter.client;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import com.aliateck.fact.domaine.ports.spi.client.ClientSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientApiAdapter implements ClientApiService{

	ClientSpiService clientSpiService;
	
	@Override
	public void addClient(Client client) {
		clientSpiService.addClient(client);
		
	}

	@Override
	public void deleteClient(Client client) {
		clientSpiService.removeClient(client);
		
	}

	@Override
	public void updateClient(Client client) {
		clientSpiService.updateClient(client);
		
	}

	@Override
	public List<Client> findAllClients() {
		return clientSpiService.findAllClients();
	}

	@Override
	public Client findById(long id) {
		return clientSpiService.findClientById(id);
	}
		

}

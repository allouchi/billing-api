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
	public void ajouterClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mettreAJourClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> retournerClients() {
		return clientSpiService.getAllClients();
	}

	@Override
	public Client chercherClientParId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client chercherClientParRaisonSociale(String socialResean) {
		clientSpiService.getClientBySocialReason(socialResean);
		return null;
	}	

}

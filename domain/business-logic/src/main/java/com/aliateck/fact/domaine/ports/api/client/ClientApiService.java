package com.aliateck.fact.domaine.ports.api.client;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Client;


public interface ClientApiService {
	
	public void ajouterClient(Client client) ;

	public void supprimerClient(Client client);

	public void mettreAJourClient(Client client);

	public List<Client> retournerClients();

	public Client chercherClientParId(long id);
	
	public Client chercherClientParRaisonSociale(String raisonSociale);

}

package com.aliateck.fact.domaine.ports.api.client;

import com.aliateck.fact.domaine.business.object.Client;

import java.util.List;

public interface ClientApiService {
    public Client addClient(Client client, String siret);

    public void deleteById(long id);

    public Client updateClient(Client client, String siret);

    public List<Client> findAllClientsBySiret(String siret);

    public List<Client> findAllClients();

    public Client findById(long id);
}

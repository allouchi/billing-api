package com.sbatec.fact.domaine.adapter.client;

import com.sbatec.fact.domaine.business.object.Client;
import com.sbatec.fact.domaine.ports.api.client.ClientApiService;
import com.sbatec.fact.domaine.ports.spi.client.ClientSpiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Client> findAllClients() {
        return clientSpiService.findAll();
    }

    @Override
    public List<Client> findAllClientsBySiret(String siret) {
        return clientSpiService.findAllBySiret(siret);
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

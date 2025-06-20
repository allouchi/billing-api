package com.sbatec.fact.application.rest.controllers.client;

import com.sbatec.fact.domaine.business.object.Client;
import com.sbatec.fact.domaine.ports.api.client.ClientApiService;
import com.sbatec.util.CommonResource.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Resource.CLIENTS)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientController {
    private ClientApiService clientApiService;

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping(value = "/{siret}")
    public List<Client> getAllClientsBySiret(@PathVariable @NotNull String siret) {
        log.info("get all clients by siret : {} ", siret);
        return clientApiService.findAllClientsBySiret(siret);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping
    public List<Client> getAllClients() {
        log.info("get all clients");
        return clientApiService.findAllClients();
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @PostMapping("/{siret}")
    public Client addClient(@RequestBody Client clientRequest, @PathVariable @NotNull String siret) {
        log.info("Create new client");
        return clientApiService.addClient(clientRequest, siret);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable @NotNull long id) {
        log.info("delete client by id  {}:", id);
        clientApiService.deleteById(id);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @PutMapping("/{siret}")
    public Client updateClient(@PathVariable(name = "siret") @NotNull String siret, @RequestBody Client clientRequest) {
        log.info("update client by id  {}:", clientRequest.getId());
        return clientApiService.updateClient(clientRequest, siret);
    }
}

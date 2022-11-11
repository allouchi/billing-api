package com.aliateck.fact.application.rest.controllers.client;

import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import com.aliateck.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Resource.CLIENTS)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientController {
  private ClientApiService clientApiService;

  @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
  @GetMapping(value = "/{siret}")
  public List<Client> getAllClients(@PathVariable String siret) {
    log.info("get all clients");
    return clientApiService.findAllClients(siret);

  }

  @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
  @PostMapping(value = "/{siret}")
  public Client addClient(@RequestBody Client clientRequest, @PathVariable String siret) {
    log.info("Create new client");
    return clientApiService.addClient(clientRequest, siret);
  }

  @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
  @DeleteMapping(value = "/{id}")
  public void deleteClient(@PathVariable long id) {
    log.info("delete client by id :" + id);
    clientApiService.deleteById(id);
  }

  @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
  @PutMapping(value = "/{siret}")
  public Client updateClient(@PathVariable String siret, @RequestBody Client clientRequest) {
    log.info("update client by id :" + clientRequest.getId());
    return clientApiService.updateClient(clientRequest, siret);
  }
}

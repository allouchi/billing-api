package com.aliateck.fact.application.controllers.client;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientController {
  ClientApiService clientApiService;

  @GetMapping
  public List<Client> getAllClients() {
    List<Client> clients = clientApiService.retournerClients();
    return clients;
  }

  @GetMapping(value = "/{raisonSociale}")
  public ResponseEntity<Client> getClient(@PathVariable String raisonSociale) {
    Client client = clientApiService.chercherClientParRaisonSociale(raisonSociale);
    return ResponseEntity.ok(client);
  }
}

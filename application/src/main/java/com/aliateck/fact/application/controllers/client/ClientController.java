package com.aliateck.fact.application.controllers.client;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientController {
  private ClientApiService clientApiService;
  
  @GetMapping(value = "/{siret}")
  public ResponseEntity<List<Client>> getAllClients(@PathVariable String siret) {
	  log.info("get all clients");
	  List<Client> clients = clientApiService.findAllClients(siret);
	  if(clients != null) {
		  log.info("nb clients : " + clients.size());
		  return ResponseEntity.ok(clients); 
	  }
	  return null;	     
  }

  @PostMapping(value = "/{siret}")
  public ResponseEntity<Client> addClient(
    @RequestBody Client clientRequest,
    @PathVariable String siret
  ) {
    log.info("Create new client");
    return ResponseEntity.ok(clientApiService.addClient(clientRequest, siret));
  }
  
  @DeleteMapping(value = "/{id}")
  public void deleteClient(
    @PathVariable long id    
  ) {
    log.info("delete client by id :" + id);    
    clientApiService.deleteById(id);    
  } 
  
  @PutMapping(value = "/{siret}")
  public void updateClient(
    @PathVariable String siret,
    @RequestBody Client clientRequest  
  ) {
    log.info("update client by id :" + clientRequest.getId());    
    clientApiService.updateClient(clientRequest, siret);    
  } 
}

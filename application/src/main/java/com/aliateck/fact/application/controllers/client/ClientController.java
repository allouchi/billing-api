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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  private CompanyApiService companyApiService;

  @GetMapping(value = "/{siret}")
  public ResponseEntity<List<Client>> getAllClients(@PathVariable String siret) {
    Company company = companyApiService.getCompanyBySiret(siret);
    return ResponseEntity.ok(company.getClients());
  }

  @PostMapping(value = "/{siret}")
  public ResponseEntity<Client> addClient(
    @RequestBody Client clientRequest,
    @PathVariable String siret
  ) {
    log.info("Create new client");
    return ResponseEntity.ok(clientApiService.addClient(clientRequest, siret));
  }
}

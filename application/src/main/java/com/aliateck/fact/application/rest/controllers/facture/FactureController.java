package com.aliateck.fact.application.rest.controllers.facture;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aliateck.fact.application.rest.util.StorageProperties;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Resource.FACTURES)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureController {

  FactureApiService factureApiService;
  @Autowired
  StorageProperties resources;

  @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
  @GetMapping(value = "/{siret}")
  public ResponseEntity<List<Facture>> findAllBySiret(@PathVariable String siret) {
    log.info("get all bills by siret");
    List<Facture> reponse = factureApiService.findAllBySiret(siret);
    if (reponse != null && !reponse.isEmpty()) {
      reponse = reponse.stream().sorted(Comparator.comparingLong(Facture::getId).reversed())
          .collect(Collectors.toList());
      return ResponseEntity.ok(reponse);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
  @DeleteMapping(value = "/{factureId}")
  public void deleteFacture(@PathVariable Long factureId) {
    log.info("delete bill");
    factureApiService.deleteFacture(factureId);
  }

  @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
  @PutMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<Facture> updateFacture(@RequestBody Facture factureRequest) {
    log.info("Update facture : " + factureRequest.getDateEncaissement());
    Facture reponse = factureApiService.updateFacture(factureRequest, resources.getPathRoot(),
        resources.getFichierSuiviFactures());
    if (reponse != null) {
      return ResponseEntity.ok(reponse);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}

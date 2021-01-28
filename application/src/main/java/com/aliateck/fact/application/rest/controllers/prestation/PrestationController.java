package com.aliateck.fact.application.rest.controllers.prestation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.application.rest.config.StorageProperties;
import com.aliateck.fact.application.rest.controllers.common.CommonResource.Resource;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.api.prestation.PrestationApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Resource.PRESTATIONS)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationController {
  
  private PrestationApiService prestationApiService;
  FactureApiService factureApiService;
  StorageProperties resources;

  @GetMapping(value = "/{siret}")
  public ResponseEntity<List<Prestation>> getAllPrestations(@PathVariable String siret) {
    log.info("get all prestations");
    List<Prestation> listPrestats = prestationApiService.findAll(siret);
    return ResponseEntity.ok(listPrestats);
  }

  @PostMapping(value = "/{siret}")
  public ResponseEntity<Prestation> addPrestation(
    @RequestBody Prestation prestation,
    @PathVariable String siret
  ) {
    log.info("Create new Prestation");
    Prestation presta = prestationApiService.addPrestation(prestation, siret);
    return ResponseEntity.ok(presta);
  }

  @PutMapping(value = "/{siret}")
  public ResponseEntity<Prestation> updatePrestation(
    @RequestBody Prestation prestationRequest,
    @PathVariable String siret
  ) {
    log.info("Update prestation");    
    Prestation presta = factureApiService.addFacture(siret, prestationRequest, prestationRequest.getId(), resources.getPathRoot());    
    return ResponseEntity.ok(presta);
  }

  @DeleteMapping(value = "/{id}")
  public boolean deletePrestation(@PathVariable long id) {
    log.info("delete prestation by id :" + id);
    prestationApiService.deletePrestation(id);
    return true;
  }
}
package com.aliateck.fact.application.controllers.prestation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.edition.EditionFactureService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.api.prestation.PrestationApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/prestation")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationController {
  FactureApiService factureApiService;
  CompanyApiService companyApiService;
  EditionFactureService editionFactureService;
  PrestationApiService prestationApiService;

  @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Prestation> addPrestation(@RequestBody Prestation prestation) {
    Prestation presta = prestationApiService.ajouterPrestation(prestation);
    return ResponseEntity.ok(presta);
  }  
}

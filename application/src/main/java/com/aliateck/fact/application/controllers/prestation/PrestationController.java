package com.aliateck.fact.application.controllers.prestation;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.edition.EditionFactureService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.api.prestation.PrestationApiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prestation")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationController {
  FactureApiService factureApiService;
  CompanyApiService companyApiService;
  EditionFactureService editionFactureService;
  PrestationApiService prestationApiService;

  @GetMapping(value = "/{siret}")
  public ResponseEntity<List<Prestation>> getAllPrestations(@PathVariable String siret) {
    System.out.println("**********************************: " + siret);
    Company company = companyApiService.getCompanyBySiret(siret);
    return ResponseEntity.ok(company.getPrestations());
  }

  @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Prestation> addPrestation(@RequestBody Prestation prestation) {
    Prestation presta = prestationApiService.ajouterPrestation(prestation);
    return ResponseEntity.ok(presta);
  }
}

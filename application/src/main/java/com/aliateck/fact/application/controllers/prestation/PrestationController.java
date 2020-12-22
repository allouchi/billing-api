package com.aliateck.fact.application.controllers.prestation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.edition.EditionFactureService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.api.prestation.PrestationApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/prestation")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationController {
  private FactureApiService factureApiService;
  private CompanyApiService companyApiService;
  private EditionFactureService editionFactureService;
  private PrestationApiService prestationApiService;

  @GetMapping(value = "/{siret}")
  public ResponseEntity<List<Prestation>> getAllPrestations(@PathVariable String siret) {
    Company company = companyApiService.getCompanyBySiret(siret);
    return ResponseEntity.ok(company.getPrestations());
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
}

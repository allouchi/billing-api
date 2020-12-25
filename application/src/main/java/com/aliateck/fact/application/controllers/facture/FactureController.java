package com.aliateck.fact.application.controllers.facture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.edition.EditionApiService;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.api.prestation.PrestationApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/factures")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureController {
  FactureApiService factureApiService;
  CompanyApiService companyApiService;
  PrestationApiService prestationApiService;
  EditionApiService editionApiService;

  @GetMapping(value = "/{siret}")
  public List<Facture> findAllBySiret(
    @PathVariable String siret
    
  ) {
    log.info("get all bills by siret");
    return factureApiService.findAllBySiret(siret);
  }
  
  @PostMapping(value = "/{siret}/{prestationId}/{numeroCommande}")
  public Map<String, Object> addFacture(
    @RequestBody Facture factureRequest,
    @PathVariable String siret,
    @PathVariable long prestationId,
    @PathVariable String numeroCommande
  ) {
    log.info("Edit new bill");
    Facture facture = factureApiService.addFacture(factureRequest, prestationId, numeroCommande);
    byte[] pdfByte  = editionApiService.editerFacture(siret, prestationId, facture.getId());
    Map<String, Object> map = new HashMap<>();
    map.put("pdfByte", pdfByte);
    map.put("facture", facture);
    return map;
  }

  @GetMapping(value = "/{siret}/{idPrestation}")
  public List<Facture> findAllByPrestation(
    @PathVariable String siret,
    @PathVariable long idPrestation
  ) {
    log.info("get all bills by prestation");
    return factureApiService.findAllByPrestation(siret, idPrestation);
  }

  
}

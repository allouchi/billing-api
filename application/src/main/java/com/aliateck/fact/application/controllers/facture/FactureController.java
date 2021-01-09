package com.aliateck.fact.application.controllers.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.ports.api.edition.EditionApiService;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/factures")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureController {
  FactureApiService factureApiService;
  EditionApiService editionApiService;

  //HttpStatusProperties httpStatus;

  @GetMapping(value = "/{siret}")
  public List<Facture> findAllBySiret(@PathVariable String siret) {
    log.info("get all bills by siret");
    List<Facture> factures = factureApiService.findAllBySiret(siret);
    if (factures == null || factures.isEmpty()) {
      //String message = String.format(httpStatus.getStatus(), "");
      //throw new FactureNotFoundException("Factures introuvables");
    }
    return factures;
  }

  @PostMapping(
    value = "/{siret}/{prestationId}",
    consumes = "application/json",
    produces = "application/json"
  )
  public Facture addFacture(
    @RequestBody Facture factureRequest,
    @PathVariable String siret,
    @PathVariable long prestationId
  ) {
    log.info("Add new bill");
    return factureApiService.addFacture(siret, factureRequest, prestationId);
  }

  @PutMapping(value = "/{siret}/{prestationId}")
  public Map<String, Object> editerFacture(
    @RequestBody Facture factureRequest,
    @PathVariable String siret,
    @PathVariable long prestationId
  ) {
    log.info("Edit new bill : " + prestationId);
    return editionApiService.editerFacture(siret, prestationId, factureRequest);
  }

  @GetMapping(value = "/{siret}/{idPrestation}")
  public List<Facture> findAllByPrestation(
    @PathVariable String siret,
    @PathVariable long PrestationId
  ) {
    log.info("get all bills by prestation");
    return factureApiService.findAllByPrestation(siret, PrestationId);
  }

  @DeleteMapping(value = "/{siret}/{prestationId}/{factureId}")
  public void deleteFacture(
    @PathVariable String siret,
    @PathVariable long prestationId,
    @PathVariable long factureId
  ) {
    log.info("delete bill by id :" + factureId);
    factureApiService.deleteById(siret, prestationId, factureId);
  }
}

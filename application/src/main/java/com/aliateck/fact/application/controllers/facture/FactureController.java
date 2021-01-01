package com.aliateck.fact.application.controllers.facture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.config.HttpStatusProperties;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
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
  HttpStatusProperties httpStatus;	

  @GetMapping(value = "/{siret}")
  public List<Facture> findAllBySiret(
    @PathVariable String siret) {	  
    log.info("get all bills by siret");     
     List<Facture> factures = factureApiService.findAllBySiret(siret);    
    if(factures == null || factures.isEmpty()) {
		//String message = String.format(httpStatus.getStatus(), "");    	
		throw new FactureNotFoundException("Factures introuvables");
	}
    return factures;
    
  }
  
  @PostMapping(value = "/{siret}/{prestationId}/{numeroCommande}")
  public Facture addFacture(
    @RequestBody Facture factureRequest,
    @PathVariable String siret,
    @PathVariable long prestationId,
    @PathVariable String numeroCommande
  ) {
    log.info("Add new bill");
    return factureApiService.addFacture(factureRequest, prestationId, numeroCommande);  
  }
  
  @PutMapping(value = "/{siret}/{prestationId}")
  public byte[] editerFacture(
    @RequestBody Facture factureRequest,
    @PathVariable String siret,
    @PathVariable long prestationId
    
  ) {
    log.info("Edit new bill");   
    return editionApiService.editerFacture(siret, prestationId, factureRequest.getId()); 
    
  }

  @GetMapping(value = "/{siret}/{idPrestation}")
  public List<Facture> findAllByPrestation(
    @PathVariable String siret,
    @PathVariable long idPrestation
  ) {
    log.info("get all bills by prestation");
    return factureApiService.findAllByPrestation(siret, idPrestation);
  }
  
  
  @DeleteMapping(value = "/{id}")
  public boolean deleteFacture(
    @PathVariable long id    
  ) {
    log.info("delete bill by id :" + id);    
    factureApiService.deleteById(id);
    return true;
  } 

  
}

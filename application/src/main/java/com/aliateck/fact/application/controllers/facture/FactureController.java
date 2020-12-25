package com.aliateck.fact.application.controllers.facture;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.common.edition.EditionFactureService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;

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
  EditionFactureService editionFactureService;

  @GetMapping(value = "/{siret}")
  public List<Facture> chercherFacturesByCompanyBySiret(@PathVariable String siret) {
    return factureApiService.chercherFacturesByCompanyBySiret(siret);
  }

  @PostMapping(value = "/{siret}/{prestationId}/{numeroCommande}")
  public Facture editerFacture(
    @RequestBody Facture factureRequest,
    @PathVariable String siret,
    @PathVariable long prestationId,
    @PathVariable String numeroCommande
  ) {
    log.info("Edit new bill");
    System.out.println("*********************factureRequest************************");
    
    //    Facture facture = factureApiService.chercherFactureParNumero(numero);
    //    List<Prestation> prestations = company.getPrestations();
    //    for (Prestation prestation : prestations) {
    //      if (prestation.getId().longValue() == facture.getId().longValue()) {
    //        editionFactureService.editerFacture(company, prestation, facture);
    //      }
    //    }
    return null;
  }
  //  @GetMapping(value = "/{siret}/{numero}")
  //  public Facture editerFacture(@PathVariable String siret, @PathVariable String numero) {
  //    Company company = companyApiService.getCompanyBySiret(siret);
  //    Facture facture = factureApiService.chercherFactureParNumero(numero);
  //    List<Prestation> prestations = company.getPrestations();
  //    for (Prestation prestation : prestations) {
  //      if (prestation.getId().longValue() == facture.getId().longValue()) {
  //        editionFactureService.editerFacture(company, prestation, facture);
  //      }
  //    }
  //    return facture;
  //  }

}

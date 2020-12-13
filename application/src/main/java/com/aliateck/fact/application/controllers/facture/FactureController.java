package com.aliateck.fact.application.controllers.facture;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.common.facture.FactureStatus;
import com.aliateck.fact.common.facture.UtilFacture;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/factures")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureController {
  FactureApiService factureApiService;
  CompanyApiService companyApiService;

  @GetMapping(value = "/{siret}")
  public Facture[] chercherFacturesByCompanyBySiret(@PathVariable String siret) {
    System.out.println("******************************************************");
    List<Facture> factures = factureApiService.chercherFacturesByCompanyBySiret(siret);
    Facture[] tabFacture = null;

    if (!factures.isEmpty()) {
      tabFacture = new Facture[factures.size()];
      tabFacture = factures.toArray(tabFacture);
    }
    return tabFacture;
  }

  @GetMapping(value = "/numero/{numero}")
  public ResponseEntity<Facture> chercherFactureParNumero(@PathVariable String numero) {
    Facture facture = factureApiService.chercherFactureParNumero(numero);

    return ResponseEntity.ok(facture);
  }

  @PutMapping(value = "/{siret}/{numero}")
  public boolean editerFacture(@PathVariable String siret, @PathVariable String numero) {
    System.out.println("******************************************************");
    Company company = companyApiService.getCompanyBySiret(siret);
    Facture facture = factureApiService.chercherFactureParNumero(numero);
    UtilFacture.editerFacture(company, facture);

    return true;
  }

  @PostMapping
  public ResponseEntity<Facture> ajouterFacture() {
    Facture facture = Facture
      .builder()
      .numeroFacture("201907311001")
      .factureStatus(FactureStatus.NON.getCode())
      .build();


    factureApiService.ajouterFacture(facture);

    return ResponseEntity.ok(facture);
  }
}

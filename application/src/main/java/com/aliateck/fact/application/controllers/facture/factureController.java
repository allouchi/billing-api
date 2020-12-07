package com.aliateck.fact.application.controllers.facture;

import com.aliateck.fact.common.facture.FactureStatus;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facture")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class factureController {
  FactureApiService factureApiService;

  @GetMapping(value = "/facture/{numero}")
  public ResponseEntity<Facture> chercherFactureParNumero(@PathVariable String numero) {
    Facture facture = factureApiService.chercherFactureParNumero(numero);
    System.out.println(numero + " " + facture);
    return ResponseEntity.ok(facture);
  }

  @GetMapping(value = "/facture/{id}")
  public ResponseEntity<Facture> chercherFactureParId(@PathVariable long id) {
    Facture facture = factureApiService.chercherFactureParId(id);
    System.out.println(id + " " + facture);
    return ResponseEntity.ok(facture);
  }

  @GetMapping(value = "/facture")
  public ResponseEntity<Facture> ajouterFacture() {
    System.out.println("**********************************");

    Facture facture = Facture
      .builder()
      .fraisRetard(60)
      .nbJourRetard(60)
      .numeroFacture("201907311001")
      .factureStatus(FactureStatus.NON.getCode())
      .build();

    Prestation prestation = Prestation
      .builder()
      .nbJoursEffectue(23)
      .delaiPaiement(60)
      .tarif(439)
      .facture(facture)
      .build();

    factureApiService.ajouterFacture(prestation.getFacture());

    return ResponseEntity.ok(facture);
  }
}

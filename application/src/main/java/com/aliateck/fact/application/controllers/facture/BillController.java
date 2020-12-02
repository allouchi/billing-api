package com.aliateck.fact.application.controllers.facture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillController {
	
	FactureApiService billApiService;
	
	@GetMapping(value = "/bill/{numero}")
	public ResponseEntity<Facture> getFacture(@PathVariable String numero) {
		Facture bill = billApiService.chercherFactureParNumero(numero);
		System.out.println(numero + " " + bill);
		return ResponseEntity.ok(bill);
	}

}

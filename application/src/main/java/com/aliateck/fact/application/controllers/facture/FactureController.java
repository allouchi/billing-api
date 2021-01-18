package com.aliateck.fact.application.controllers.facture;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.config.StorageProperties;
import com.aliateck.fact.domaine.business.object.Facture;
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
	StorageProperties resources;

	@GetMapping(value = "/{siret}")
	public ResponseEntity<List<Facture>> findAllBySiret(@PathVariable String siret) {
		log.info("get all bills by siret");
		List<Facture> reponse = factureApiService.findAllBySiret(siret);
		if (reponse != null && !reponse.isEmpty()) {
			return ResponseEntity.ok(reponse);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}	

	@DeleteMapping(value = "/{factureId}")
	public void deleteFacture(@PathVariable Long factureId) {
		log.info("delete bill");
		factureApiService.deleteFacture(factureId);
	}

	@PostMapping(value = "/{siret}/{prestationId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Facture> addFacture(@RequestBody Facture factureRequest, @PathVariable String siret,
			@PathVariable long prestationId) {
		log.info("Add new bill");
		/*
		Facture reponse = factureApiService.addFacture(siret, factureRequest, prestationId, resources.getPathRoot());
		if (reponse != null) {
			return ResponseEntity.ok(reponse);
		}
		*/
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Facture> updateFacture(@RequestBody Facture factureRequest) {
		log.info("Update facture : "+ factureRequest.getDateEncaissement());
		Facture reponse = factureApiService.updateFacture(factureRequest);
		if (reponse != null) {
			return ResponseEntity.ok(reponse);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}	

}

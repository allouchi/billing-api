package com.aliateck.fact.application.controllers.facture;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.config.ResourcesProperties;
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
	ResourcesProperties resources;

	@GetMapping(value = "/{siret}")
	public ResponseEntity<List<Facture>> findAllBySiret(@PathVariable String siret) {
		log.info("get all bills by siret");
		List<Facture> reponse = factureApiService.findAllBySiret(siret);
		if (reponse != null && !reponse.isEmpty()) {
			return ResponseEntity.ok(reponse);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{siret}/{idPrestation}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType. APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Facture>> findAllByPrestation(@PathVariable String siret,
			@PathVariable Long prestationId) {
		log.info("get all bills by prestation");
		List<Facture> reponse = factureApiService.findAllByPrestation(siret, prestationId);
		if (reponse != null && !reponse.isEmpty()) {
			return ResponseEntity.ok(reponse);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{siret}/{prestationId}/{factureId}")
	public void deleteFacture(@PathVariable String siret, @PathVariable long prestationId,
			@PathVariable Long factureId) {
		log.info("delete bill by id :" + factureId);
		factureApiService.deleteById(siret, prestationId, factureId);
	}

	@PostMapping(value = "/{siret}/{prestationId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Facture> addFacture(@RequestBody Facture factureRequest, @PathVariable String siret,
			@PathVariable long prestationId) {
		log.info("Add new bill");
		Facture reponse = factureApiService.addFacture(siret, factureRequest, prestationId, resources.getPathFile());
		if (reponse != null) {
			return ResponseEntity.ok(reponse);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value = "/{siret}/{prestationId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Facture> updateFacture(@RequestBody Facture factureRequest, @PathVariable String siret,
			@PathVariable Long prestationId
			) {
		log.info("Update facture : "+ factureRequest.getDateEncaissement());
		Facture reponse = factureApiService.updateFacture(siret, factureRequest, prestationId);
		if (reponse != null) {
			return ResponseEntity.ok(reponse);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}	

}

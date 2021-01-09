package com.aliateck.fact.application.controllers.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.ports.api.edition.EditionApiService;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
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

@RestController
@RequestMapping("/factures")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureController {
	FactureApiService factureApiService;
	EditionApiService editionApiService;

	@Value("${spring.files.paths}")
	private String path;

	public String getPath() {
		return path;
	}

	// HttpStatusProperties httpStatus;

	@GetMapping(value = "/{siret}")
	public ResponseEntity<List<Facture>> findAllBySiret(@PathVariable String siret) {
		log.info("get all bills by siret");
		System.out.println("**************************** : " + getPath());
		List<Facture> reponse = factureApiService.findAllBySiret(siret);
		if (reponse != null && !reponse.isEmpty()) {
			return ResponseEntity.ok(reponse);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/{siret}/{prestationId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Facture> addFacture(@RequestBody Facture factureRequest, @PathVariable String siret,
			@PathVariable long prestationId) {
		log.info("Add new bill");
		Facture reponse = factureApiService.addFacture(siret, factureRequest, prestationId);
		if (reponse != null) {
			return ResponseEntity.ok(reponse);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/{siret}/{prestationId}")
	public ResponseEntity<Facture> editerFacture(@RequestBody Facture factureRequest, @PathVariable String siret,
			@PathVariable long prestationId) {
		log.info("Edit new bill : " + prestationId);
		Facture reponse = editionApiService.editerFacture(siret, prestationId, factureRequest);
		if (reponse != null) {
			return ResponseEntity.ok(reponse);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{siret}/{idPrestation}")
	public ResponseEntity<List<Facture>> findAllByPrestation(@PathVariable String siret,
			@PathVariable long prestationId) {
		log.info("get all bills by prestation");
		List<Facture> reponse = factureApiService.findAllByPrestation(siret, prestationId);
		if (reponse != null && !reponse.isEmpty()) {
			return ResponseEntity.ok(reponse);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{siret}/{prestationId}/{factureId}")
	public void deleteFacture(@PathVariable String siret, @PathVariable long prestationId,
			@PathVariable long factureId) {
		log.info("delete bill by id :" + factureId);
		factureApiService.deleteById(siret, prestationId, factureId);
	}

	@GetMapping(value = "/{path}", produces = "application/pdf")
	public ResponseEntity<byte[]> downloadPdf(@PathVariable String path) {
		log.info("get pdf by path name file");
		byte[] contents = editionApiService.downloadPdf(path);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "test.pdf";
		headers.setContentDispositionFormData(filename, filename);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
		return response;
	}
}

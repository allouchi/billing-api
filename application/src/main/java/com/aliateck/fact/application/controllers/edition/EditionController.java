package com.aliateck.fact.application.controllers.edition;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.config.ResourcesProperties;
import com.aliateck.fact.domaine.business.object.DataPDF;
import com.aliateck.fact.domaine.ports.api.edition.EditionApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/editions")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionController {

	EditionApiService editionApiService;
	ResourcesProperties resources;
	
	@GetMapping(value = "/{siret}/{prestationId}/{factureId}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public DataPDF downloadPdf(@PathVariable String siret, @PathVariable Long prestationId,
			@PathVariable Long factureId) {
		log.info("get pdf by path name file");
		DataPDF reponse = editionApiService.downloadPdf(siret, prestationId, factureId, resources.getPathFile());
		if (reponse != null) {
			return reponse;
		}
		return null;
	}

}

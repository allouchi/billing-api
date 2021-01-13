package com.aliateck.fact.application.controllers.edition;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.config.MediaTypeUtils;
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
	@Autowired
	ServletContext servletContext;

	@GetMapping(value = "/{siret}/{prestationId}/{factureId}")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadPdf(@PathVariable String siret, @PathVariable Long prestationId,
			@PathVariable Long factureId) {
		log.info("get pdf file by pathName");

		DataPDF reponse = editionApiService.downloadPdf(siret, prestationId, factureId, resources.getPathFile());
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, reponse.getFileName());
		ByteArrayResource resource = new ByteArrayResource(reponse.getFileContent());
		HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+ reponse.getFileName());
        //header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        //header.add("Pragma", "no-cache");
        //header.add("Expires", "0");
        
		return ResponseEntity.ok()				
				.headers(header)				
				.contentType(mediaType) 				
				.contentLength(reponse.getFileContent().length)
				.body(resource);
		

	}

}

package com.aliateck.fact.application.rest.controllers.edition;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.application.rest.util.MediaTypeUtils;
import com.aliateck.fact.application.rest.util.StorageProperties;
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
	@Autowired
	StorageProperties resources;

	@GetMapping(value = "/{factureId}")
	public ResponseEntity<ByteArrayResource> downloadPdf(@PathVariable Long factureId, HttpServletRequest request) {
		log.info("get pdf file by facture id : " + factureId);

		DataPDF reponse = editionApiService.downloadPdf(factureId, resources.getPathRoot());
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(request.getServletContext(),
				reponse.getFileName());
		ByteArrayResource resource = new ByteArrayResource(reponse.getFileContent());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=facture.pdf");

		return ResponseEntity.ok().headers(headers).contentType(mediaType)
				.contentLength(resource.contentLength()).body(resource);

	}

}

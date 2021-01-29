package com.aliateck.fact.application.rest.controllers.edition;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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

import com.aliateck.fact.application.rest.config.MediaTypeUtils;
import com.aliateck.fact.application.rest.config.StorageProperties;
import com.aliateck.fact.application.rest.controllers.common.CommonResource.Resource;
import com.aliateck.fact.domaine.business.object.DataPDF;
import com.aliateck.fact.domaine.ports.api.edition.EditionApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Resource.EDITIONS)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionController {

	EditionApiService editionApiService;
	@Autowired
	StorageProperties resources;
	

	@GetMapping(value = "/{factureId}")	
	public ResponseEntity<ByteArrayResource> downloadPdf(
			@PathVariable Long factureId, HttpServletRequest request) throws FileNotFoundException {
		log.info("get pdf file by facture id : " + factureId);

		DataPDF reponse = editionApiService.downloadPdf(factureId, resources.getPathRoot());
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(request.getServletContext(), reponse.getFileName());
		ByteArrayResource resource = new ByteArrayResource(reponse.getFileContent());		
		
		
/*
		return ResponseEntity.ok().contentType(mediaType).contentLength(reponse.getFileContent().length)
				.body(resource);
		*/
		 return ResponseEntity.ok()
	                // Content-Disposition
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + reponse.getFileName())
	                // Content-Type
	                .contentType(mediaType) //
	                // Content-Lengh
	                .contentLength(reponse.getFileContent().length) //
	                .body(resource);

	}

}

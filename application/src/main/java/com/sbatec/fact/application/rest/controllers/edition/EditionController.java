package com.sbatec.fact.application.rest.controllers.edition;

import com.sbatec.fact.domaine.business.object.DataPDF;
import com.sbatec.fact.domaine.ports.api.edition.EditionApiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionController {

    EditionApiService editionApiService;

    @Secured({"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping(value = "/editions/{id}")
    public ResponseEntity<DataPDF> downloadPdf(@PathVariable Long id) {
        DataPDF pdfData = editionApiService.downloadPdf(id);
        return new ResponseEntity<>(pdfData, HttpStatus.OK);
    }
}

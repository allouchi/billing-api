package com.sbatec.fact.application.rest.controllers.edition;

import com.sbatec.fact.domaine.business.object.DataPDF;
import com.sbatec.fact.domaine.ports.api.edition.EditionApiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionController {

    EditionApiService editionApiService;

    @Secured({"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping(value = "/editions/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        log.info("Get PDF File Facture Id : {}", id);

        DataPDF reponse = editionApiService.downloadPdf(id);
        try {
            if (reponse != null && reponse.getFileContent() != null && reponse.getFileContent().length > 0) {
                byte[] data = reponse.getFileContent();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                String encodedFileName = URLEncoder.encode(reponse.getFileName(), StandardCharsets.UTF_8)
                        .replaceAll("\\+", "%20");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName);
                headers.setContentLength(data.length);
                return new ResponseEntity<>(data, headers, HttpStatus.OK);
            }

        } catch (Exception e) {
            log.error("Error when loading pdf file : {}", e);
        }
        return ResponseEntity.notFound().build();
    }
}

package com.sbatec.fact.application.rest.controllers.edition;

import com.sbatec.fact.application.rest.util.MediaTypeUtils;
import com.sbatec.fact.domaine.business.object.DataPDF;
import com.sbatec.fact.domaine.ports.api.edition.EditionApiService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    @GetMapping(value = "/editions/{factureId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long factureId, HttpServletRequest request,
                                              HttpServletResponse response) {
        log.info("Get PDF File By Id : " + factureId);

        DataPDF reponse = editionApiService.downloadPdf(factureId);

        if (reponse != null && reponse.getFileContent() != null && reponse.getFileContent().length > 0) {
            MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(request.getServletContext(),
                    reponse.getFileName());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + reponse.getFileName());
            headers.setContentType(mediaType);
            return ResponseEntity.ok().headers(headers).contentType(mediaType)
                    .contentLength(reponse.getFileContent().length).body(reponse.getFileContent());
        }
        return ResponseEntity.notFound().build();
    }
}

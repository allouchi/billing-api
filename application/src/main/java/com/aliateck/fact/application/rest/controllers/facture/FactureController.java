package com.aliateck.fact.application.rest.controllers.facture;

import com.aliateck.fact.application.rest.util.StorageProperties;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.util.CommonResource.Resource;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Resource.FACTURES)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureController {

    FactureApiService factureApiService;
    @Autowired
    StorageProperties resources;

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping("/{siret}")
    public List<Facture> findAllBySiret(@PathVariable @NotNull String siret) {
        log.info("get all bills by siret");
        return factureApiService.findFacturesBySiret(siret);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping("/{siret}/exercice")
    public List<Facture> findAllBySiretAndExercice(@PathVariable @NotNull String siret, @PathVariable @NotNull String exercice) {
        log.info("get all bills by siret {} and exercice {}", siret, exercice);
        List<Facture> factures = factureApiService.findFacturesBySiret(siret);
        return factures;
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @DeleteMapping("/{factureId}")
    public void deleteFacture(@PathVariable @Min(1) Long factureId) {
        log.info("delete bill");
        factureApiService.deleteFacture(factureId);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @PutMapping(consumes = "application/json", produces = "application/json")
    public Facture updateFacture(@RequestBody @NotBlank Facture factureRequest) {
        log.info("Update facture : " + factureRequest.getDateEncaissement());
        return factureApiService.updateFacture(factureRequest, resources.getPathRoot(),
                resources.getFichierSuiviFactures());
    }
}

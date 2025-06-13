package com.sbatec.fact.application.rest.controllers.facture;

import com.sbatec.fact.application.rest.util.StorageProperties;
import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.business.object.Prestation;
import com.sbatec.fact.domaine.ports.api.facture.FactureApiService;
import com.sbatec.util.CommonResource.Resource;
import jakarta.validation.constraints.Min;
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
        List<Facture> factures = factureApiService.findFacturesBySiret(siret);
        return factures;
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
    @PutMapping(value = "/{siret}/{moisFacture}/{newTemplate}")
    public Prestation createFacture(@RequestBody Prestation prestation,
                                    @PathVariable @NotNull String siret,
                                    @PathVariable @NotNull Long moisFacture,
                                    @PathVariable Boolean newTemplate) {
        log.info("Create facture");
        return factureApiService.addFacture(siret, newTemplate, prestation,
                resources.getPathRoot(), moisFacture, resources.saveFileLocalDisque(),
                resources.getFichierSuiviFactures());
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @PutMapping(consumes = "application/json", produces = "application/json")
    public Facture updateFacture(@RequestBody @NotNull Facture factureRequest) {
        log.info("Update facture : " + factureRequest.getDateEncaissement());
        return factureApiService.updateFacture(factureRequest, resources.getPathRoot(),
                resources.getFichierSuiviFactures());
    }
}

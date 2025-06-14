package com.sbatec.fact.application.rest.controllers.prestation;

import com.sbatec.fact.application.rest.util.StorageProperties;
import com.sbatec.fact.domaine.business.object.Prestation;
import com.sbatec.fact.domaine.ports.api.facture.FactureApiService;
import com.sbatec.fact.domaine.ports.api.prestation.PrestationApiService;
import com.sbatec.util.CommonResource.Resource;
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
@RequestMapping(Resource.PRESTATIONS)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationController {

    PrestationApiService prestationApiService;
    FactureApiService factureApiService;
    StorageProperties resources;

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping(value="/{siret}")
    public List<Prestation> getAllPrestations(@PathVariable @NotNull String siret) {
        log.info("get all prestations");
        List<Prestation> prestations = prestationApiService.findAll(siret);
        return prestations;
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @PostMapping(value = "/{siret}")
    public Prestation addPrestation(@RequestBody Prestation prestation,
                                    @PathVariable @NotNull String siret) {
        log.info("Create new Prestation");
        return prestationApiService.addPrestation(prestation, siret);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @PutMapping()
    public Prestation updatePrestation(@RequestBody Prestation prestation) {
        log.info("Create or update Prestation");
        return prestationApiService.updatePrestation(prestation);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @DeleteMapping(value = "/{id}")
    public void deletePrestation(@PathVariable @NotNull long id) {
        log.info("delete prestation by id :" + id);
        prestationApiService.deletePrestation(id);
    }
}

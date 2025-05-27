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
    @Autowired
    StorageProperties resources;

    @GetMapping("/{siret}")
    public List<Prestation> getAllPrestations(@PathVariable String siret) {
        log.info("get all prestations");
        List<Prestation> prestations = prestationApiService.findAll(siret);
        return prestations;
    }

    @PostMapping(value = "/{siret}")
    public Prestation addPrestation(@RequestBody Prestation prestation,
                                    @PathVariable @NotNull String siret) {
        log.info("Create new Prestation");
        return prestationApiService.addPrestation(prestation, siret);
    }

    @PutMapping(value = "/{siret}")
    public Prestation updatePrestation(@RequestBody Prestation prestation,
                                       @PathVariable @NotNull String siret) {
        log.info("Create or update Prestation");
        return prestationApiService.updatePrestation(prestation, siret);
    }

    @PutMapping(value = "/{siret}/{templateChoice}/{moisPrestaId}")
    public Prestation createFacture(@RequestBody Prestation prestation,
                                    @PathVariable @NotNull String siret,
                                    @PathVariable Boolean templateChoice,
                                    @PathVariable @NotNull Long moisPrestaId) {
        log.info("Create prestation");
        return factureApiService.addFacture(siret, templateChoice, prestation,
                resources.getPathRoot(), moisPrestaId, resources.saveFileLocalDisque(),
                resources.getFichierSuiviFactures());
    }

    @DeleteMapping(value = "/{id}")
    public void deletePrestation(@PathVariable @NotNull long id) {
        log.info("delete prestation by id :" + id);
        prestationApiService.deletePrestation(id);
    }
}

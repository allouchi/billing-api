package com.sbatec.fact.application.rest.controllers.tva;

import com.sbatec.fact.domaine.business.object.Tva;
import com.sbatec.fact.domaine.business.object.TvaInfo;
import com.sbatec.fact.domaine.ports.api.tva.TvaApiService;
import com.sbatec.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(Resource.TVAS)
public class TvaController {

    TvaApiService tvaApiService;

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping("/{siret}/{exercise}")
    public List<Tva> getByExercise(@PathVariable String siret, @PathVariable String exercise) {
        log.info("---- Get All tvas by exercise : " + exercise);
        return tvaApiService.findByExerciseAndSiret(exercise, siret);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping(Resource.TVASINFO + "/{siret}/{exercise}")
    public TvaInfo getTvaInfoByExercise(@PathVariable String exercise, @PathVariable String siret) {
        log.info("---- Get All tvas info by exercise : " + exercise);
        return tvaApiService.findTvaInfo(exercise, siret);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @PutMapping(consumes = "application/json", produces = "application/json")
    public void updateTva(@RequestBody Tva tvaRequest) {
        log.info("---- add or update tva : " + tvaRequest);
        tvaApiService.updateTva(tvaRequest);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @DeleteMapping(value = "/{id}")
    public void deleteTva(@PathVariable Long id) {
        log.info("---- delete by id : " + id);
        tvaApiService.deleteTva(id);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @PostMapping(consumes = "application/json", produces = "application/json")
    public void addTva(@RequestBody Tva tvaRequest) {
        log.info("---- add or update tva : " + tvaRequest);
        tvaApiService.addTva(tvaRequest);
    }

}

package com.aliateck.fact.application.rest.controllers.tva;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.business.object.TvaInfo;
import com.aliateck.fact.domaine.ports.api.tva.TvaApiService;
import com.aliateck.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(Resource.TVAS)
public class TvaController {

    TvaApiService tvaApiService;

    @GetMapping("/{exercise}")
    public List<Tva> getByExercise(@PathVariable String exercise) {
        log.info("---- Get All tvas by exercise : " + exercise);
        return tvaApiService.findByExercise(exercise);
    }

    @GetMapping(Resource.TVASINFO + "/{exercise}")
    public TvaInfo getTvaInfoByExercise(@PathVariable String exercise) {
        log.info("---- Get All tvas info by exercise : " + exercise);
        return tvaApiService.findTvaInfo(exercise);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public void updateTva(@RequestBody Tva tvaRequest) {
        log.info("---- add or update tva : " + tvaRequest);
        tvaApiService.updateTva(tvaRequest);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTva(@PathVariable Long id) {
        log.info("---- delete by id : " + id);
        tvaApiService.deleteTva(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void addTva(@RequestBody Tva tvaRequest) {
        log.info("---- add or update tva : " + tvaRequest);
        tvaApiService.addTva(tvaRequest);
    }

}

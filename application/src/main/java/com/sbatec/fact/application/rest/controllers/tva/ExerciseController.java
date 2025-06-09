package com.sbatec.fact.application.rest.controllers.tva;

import com.sbatec.fact.domaine.business.object.Exercise;
import com.sbatec.fact.domaine.ports.api.tva.ExerciseApiService;
import com.sbatec.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(Resource.EXERCISES)
public class ExerciseController {

    ExerciseApiService exerciseApiService;

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping("/exerciceRef")
    public List<Exercise> findAllExercises() {
        log.info("find all exercises ref");
        List<Exercise> reponse = exerciseApiService.findExercisesRef();
        return reponse.stream().sorted(Comparator.comparing(Exercise::getExercise).reversed())
                .collect(Collectors.toList());
    }
}

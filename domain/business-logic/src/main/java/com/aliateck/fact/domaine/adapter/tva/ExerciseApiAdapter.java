package com.aliateck.fact.domaine.adapter.tva;

import com.aliateck.fact.domaine.business.object.Exercise;
import com.aliateck.fact.domaine.ports.api.tva.ExerciseApiService;
import com.aliateck.fact.domaine.ports.spi.tva.ExerciseSpiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExerciseApiAdapter implements ExerciseApiService {

    ExerciseSpiService exerciseSpiService;

    @Override
    public List<Exercise> findExercisesRef() {
        return exerciseSpiService.findExercisesRef();
    }

}

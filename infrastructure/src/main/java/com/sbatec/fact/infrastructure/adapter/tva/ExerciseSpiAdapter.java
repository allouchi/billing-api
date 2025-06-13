package com.sbatec.fact.infrastructure.adapter.tva;

import com.sbatec.fact.domaine.business.object.Exercise;
import com.sbatec.fact.domaine.ports.spi.tva.ExerciseSpiService;
import com.sbatec.fact.infrastructure.mapper.ExerciseMapper;
import com.sbatec.fact.infrastructure.models.ExerciseEntity;
import com.sbatec.fact.infrastructure.repository.tva.ExerciseJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
//@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExerciseSpiAdapter implements ExerciseSpiService {

    ExerciseMapper exerciseMapper;
    ExerciseJpaRepository exerciseJpaRepository;

    @Override
    public void delteByExercise(String exercice) {

    }

    @Override
    public void delteById(Long id) {

    }

    @Override
    public void updateExercise(Exercise exercise) {

    }

    @Override
    public Exercise findByExercise(String exercice) {
        return null;
    }

    @Override
    public Exercise addExercise(Exercise exercise) {
        ExerciseEntity entity = exerciseJpaRepository.save(exerciseMapper.fromDomainToEntity(exercise));
        return exerciseMapper.fromEntityToDomain(entity);

    }

    @Override
    public List<Exercise> findExercisesRef() {
        List<ExerciseEntity> entities = exerciseJpaRepository.findAll();
        entities.sort(Comparator.comparing(ExerciseEntity::getId));
        return exerciseMapper.fromEntityToDomain(entities);
    }

}

package com.aliateck.fact.infrastructure.adapter.tva;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.business.object.TvaInfo;
import com.aliateck.fact.domaine.exception.TvaNotFoundException;
import com.aliateck.fact.domaine.ports.spi.tva.TvaSpiService;
import com.aliateck.fact.infrastructure.mapper.TvaMapper;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.TvaEntity;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import com.aliateck.fact.infrastructure.repository.tva.TvaJpaRepository;
import com.aliateck.util.Utils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author maliane
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TvaSpiAdapter implements TvaSpiService {

    private static final String TOUS = "Tous";

    private TvaMapper tvaMapper;
    // private ExerciseMapper exerciseMapper;
    private TvaJpaRepository tvaJpaRepository;
    // private ExerciseJpaRepository exerciseJpaRepository;
    private FactureJpaRepository factureJpaRepository;

    @Override
    public List<Tva> findByExercise(String exercise) {
        List<TvaEntity> e;
        if (exercise.equalsIgnoreCase("Tous")) {
            e = tvaJpaRepository.findAll();
        } else {
            e = tvaJpaRepository.findByExercise(exercise);
        }

        if (Objects.isNull(e)) {
            throw new TvaNotFoundException(String.format("La Tva exercice %s n'existe pas", exercise));
        }
        List<Tva> tvas = tvaMapper.fromEntityToDomain(e);
        return tvas.stream().sorted(Comparator.comparingLong(Tva::getId).reversed())
                .collect(Collectors.toList());

    }

    @Override
    public void deleteByExercise(String exercise) {
        tvaJpaRepository.findByExercise(exercise);
    }

    @Override
    public void deleteById(Long id) {
        tvaJpaRepository.deleteById(id);
    }

    @Override
    public Tva addTva(Tva tva) {
        TvaEntity entity = tvaJpaRepository.save(tvaMapper.fromDomainToEntity(tva));
        return tvaMapper.fromEntityToDomain(entity);
    }

    @Override
    public void updateTva(Tva tva) {
        String datePaiment = Utils.convertFromDomainToEntityDate(tva.getDatePayment());
        tva.setDatePayment(datePaiment);
        Optional<TvaEntity> entity = tvaJpaRepository.findById(tva.getId());
        entity.orElseThrow(() -> new TvaNotFoundException(
                String.format("La Tva numéro %s n'existe pas", tva.getId())));
        TvaEntity e = entity.get();
        e.setDatePayment(tva.getDatePayment());
        e.setMontantPayment(tva.getMontantPayment());
        tvaJpaRepository.saveAndFlush(e);
    }

    @Override
    public Tva findById(Long id) {
        Optional<TvaEntity> o = tvaJpaRepository.findById(id);
        o.orElseThrow(
                () -> new TvaNotFoundException(String.format("La Tva numéro %s n'existe pas", id)));
        return tvaMapper.fromEntityToDomain(o.get());
    }

    /**
     *
     */
    @Override
    public List<Tva> findAllTva() {
        List<TvaEntity> entities = tvaJpaRepository.findAll();
        return tvaMapper.fromEntityToDomain(entities);
    }

    @Override
    public TvaInfo findTvaInfo(String exercise) {

        List<FactureEntity> entities = null;
        TvaInfo info = new TvaInfo();

        if (exercise.equalsIgnoreCase(TOUS)) {
            entities = factureJpaRepository.findAll();
        } else {
            entities = factureJpaRepository.findByExercice(exercise);
        }
        if (entities != null) {
            float totalTvaPaye = 0;
            float totalTva = entities.stream().map(e -> e.getMontantTVA()).reduce(0f, Float::sum);
            float totalTTC = entities.stream().map(e -> e.getPrixTotalTTC()).reduce(0f, Float::sum);
            List<TvaEntity> listeTvaPayee;
            if (exercise.equalsIgnoreCase(TOUS)) {
                listeTvaPayee = tvaJpaRepository.findAll();
            } else {
                listeTvaPayee = tvaJpaRepository.findByExercise(exercise);
            }
            if (listeTvaPayee != null) {
                totalTvaPaye = listeTvaPayee.stream().map(e -> e.getMontantPayment()).reduce(0f, Float::sum);
            }
            info.setTotalTvaPaye(totalTvaPaye);
            info.setTotalTvaRestant(totalTva - totalTvaPaye);
            info.setTotalTTC(totalTTC);
        }
        return info;
    }
}

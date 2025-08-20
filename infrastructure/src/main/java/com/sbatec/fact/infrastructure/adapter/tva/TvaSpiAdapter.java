package com.sbatec.fact.infrastructure.adapter.tva;

import com.sbatec.fact.domaine.business.object.Tva;
import com.sbatec.fact.domaine.business.object.TvaInfo;
import com.sbatec.fact.domaine.exception.TvaNotFoundException;
import com.sbatec.fact.domaine.ports.spi.tva.TvaSpiService;
import com.sbatec.fact.infrastructure.mapper.TvaMapper;
import com.sbatec.fact.infrastructure.models.FactureEntity;
import com.sbatec.fact.infrastructure.models.TvaEntity;
import com.sbatec.fact.infrastructure.repository.facture.FactureJpaRepository;
import com.sbatec.fact.infrastructure.repository.tva.TvaJpaRepository;
import com.sbatec.util.Utils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author maliane
 */
@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TvaSpiAdapter implements TvaSpiService {

    static final String TOUS = "Tous";

    TvaMapper tvaMapper;
    TvaJpaRepository tvaJpaRepository;
    FactureJpaRepository factureJpaRepository;

    @Override
    public List<Tva> findByExerciseAndSiret(String exercise, String siret) {
        List<TvaEntity> tvaEntities;
        if (exercise.equalsIgnoreCase("Tous")) {
            tvaEntities = tvaJpaRepository.findBySiret(siret);
        } else {
            tvaEntities = tvaJpaRepository.findByExerciseAndSiret(exercise, siret);
        }

        if (Objects.isNull(tvaEntities)) {
            throw new TvaNotFoundException(String.format("La Tva exercice %s n'existe pas", exercise));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Tva> tvas = tvaMapper.fromEntityToDomain(tvaEntities);
        tvas.sort(Comparator.<Tva, LocalDate>comparing(
                tva -> LocalDate.parse(tva.getDatePayment(), formatter)
        ).reversed());
        return tvas;

    }

    @Override
    public List<Tva> findByExercise(String exercise) {
        return null;
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
        TvaEntity toSave = tvaMapper.fromDomainToEntity(tva);
        tvaJpaRepository.saveAndFlush(toSave);
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
    public List<Tva> findBySiret(String siret) {
        List<TvaEntity> entities = tvaJpaRepository.findBySiret(siret);
        return tvaMapper.fromEntityToDomain(entities);
    }


    @Override
    public TvaInfo findTvaInfo(String exercise, String siret) {

        List<FactureEntity> facturesEntities = null;
        List<TvaEntity> listeTvaPayee;
        TvaInfo info = new TvaInfo();
        List<FactureEntity> factures = factureJpaRepository.findBySiret(siret);

        if (!factures.isEmpty()) {
            facturesEntities = factures.stream().filter(f -> f.getDateEncaissement() != null).toList();
        }
        if (Objects.isNull(facturesEntities)) {
            throw new TvaNotFoundException(String.format("La Tva exercice %s n'existe pas", exercise));
        }

        List<FactureEntity> facturesFiltred = new ArrayList<>(facturesEntities);

        if (!exercise.equalsIgnoreCase(TOUS)) {
            Iterator<FactureEntity> it = facturesFiltred.iterator();
            while (it.hasNext()) {
                FactureEntity facture = it.next();
                if (!facture.getExercice().equals(exercise)) {
                    it.remove();
                }
            }
        }

        BigDecimal totalTvaPaye = BigDecimal.ZERO;
        BigDecimal totalTvaNet = facturesFiltred.stream().map(e -> (BigDecimal.valueOf(e.getMontantTVA() - 30))).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalTva = facturesFiltred.stream().map(e -> BigDecimal.valueOf(e.getMontantTVA())).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalTTC = facturesFiltred.stream().map(e -> BigDecimal.valueOf(e.getPrixTotalTTC())).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCAHorsTaxe = facturesFiltred.stream().map(e -> BigDecimal.valueOf(e.getPrixTotalHT())).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (exercise.equalsIgnoreCase(TOUS)) {
            listeTvaPayee = tvaJpaRepository.findBySiret(siret);
        } else {
            listeTvaPayee = tvaJpaRepository.findByExerciseAndSiret(exercise, siret);
        }
        if (listeTvaPayee != null) {
            totalTvaPaye = listeTvaPayee.stream().map(e -> e.getMontantPayment()).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        info.setTotalTvaPaye(totalTvaPaye);
        info.setTotalTva(totalTva);
        info.setTotalTvaNet(totalTvaNet);
        info.setTotalTvaRestant(totalTva.subtract(totalTvaPaye));
        info.setTotalTTC(totalTTC);
        info.setTotalCAHorsTaxe(totalCAHorsTaxe);
        return info;
    }
}

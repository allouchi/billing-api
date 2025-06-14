package com.sbatec.fact.infrastructure.adapter.tva;

import com.sbatec.fact.domaine.business.object.Tva;
import com.sbatec.fact.domaine.business.object.TvaInfo;
import com.sbatec.fact.domaine.exception.TvaNotFoundException;
import com.sbatec.fact.domaine.ports.spi.tva.TvaSpiService;
import com.sbatec.fact.infrastructure.mapper.TvaMapper;
import com.sbatec.fact.infrastructure.models.CompanyEntity;
import com.sbatec.fact.infrastructure.models.FactureEntity;
import com.sbatec.fact.infrastructure.models.PrestationEntity;
import com.sbatec.fact.infrastructure.models.TvaEntity;
import com.sbatec.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.sbatec.fact.infrastructure.repository.facture.FactureJpaRepository;
import com.sbatec.fact.infrastructure.repository.tva.TvaJpaRepository;
import com.sbatec.util.Utils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author maliane
 */
@Service
//@Transactional
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

    private CompanyJpaRepository companyJpaRepository;

    @Override
    public List<Tva> findByExerciseAndSiret(String exercise, String siret) {
        List<TvaEntity> e;
        if (exercise.equalsIgnoreCase("Tous")) {
            e = tvaJpaRepository.findBySiret(siret);
        } else {
            e = tvaJpaRepository.findByExerciseAndSiret(exercise, siret);
        }

        if (Objects.isNull(e)) {
            throw new TvaNotFoundException(String.format("La Tva exercice %s n'existe pas", exercise));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Tva> tvas = tvaMapper.fromEntityToDomain(e);
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

        List<FactureEntity> factureEntity = new ArrayList<>();
        TvaInfo info = new TvaInfo();

        Optional<CompanyEntity> company = companyJpaRepository.findBySiret(siret);
        if (company.isPresent()) {
            CompanyEntity entity = company.get();
            List<PrestationEntity> prestations = entity.getPrestations();
            for (PrestationEntity prestation : prestations) {
                for (FactureEntity factures : prestation.getFactures()) {
                    if (factures.getDateEncaissement() != null) {
                        factureEntity.add(factures);
                    }
                }
            }
        }
        if (!exercise.equalsIgnoreCase(TOUS)) {
            Iterator<FactureEntity> it = factureEntity.iterator();
            while (it.hasNext()) {
                FactureEntity facture = it.next();
                if (facture.getDateEncaissement() == null || !facture.getExercice().equals(exercise)) {
                    it.remove();
                }
            }
        }

        float totalTvaPaye = 0;
        float totalTva = factureEntity.stream().map(e -> (e.getMontantTVA() - 30)).reduce(0f, Float::sum);
        float totalTTC = factureEntity.stream().map(e -> e.getPrixTotalTTC()).reduce(0f, Float::sum);
        List<TvaEntity> listeTvaPayee;

        if (exercise.equalsIgnoreCase(TOUS)) {
            listeTvaPayee = tvaJpaRepository.findBySiret(siret);
        } else {
            listeTvaPayee = tvaJpaRepository.findByExerciseAndSiret(exercise, siret);
        }
        if (listeTvaPayee != null) {
            totalTvaPaye = listeTvaPayee.stream().map(e -> e.getMontantPayment()).reduce(0f, Float::sum);
        }
        info.setTotalTvaPaye(totalTvaPaye);
        info.setTotalTva(totalTva);
        info.setTotalTvaRestant(totalTva - totalTvaPaye);
        info.setTotalTTC(totalTTC);
        return info;
    }
}

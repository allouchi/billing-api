package com.sbatec.fact.domaine.adapter.tva;

import com.sbatec.fact.domaine.business.object.Tva;
import com.sbatec.fact.domaine.business.object.TvaInfo;
import com.sbatec.fact.domaine.ports.api.tva.TvaApiService;
import com.sbatec.fact.domaine.ports.spi.tva.TvaSpiService;
import com.sbatec.util.Utils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TvaApiAdapter implements TvaApiService {

    TvaSpiService tvaSpiService;

    @Override
    public Tva addTva(Tva tva) {
        String datePaiment = Utils.convertFromDomainToEntityDate(tva.getDatePayment());
        tva.setDatePayment(datePaiment);
        return tvaSpiService.addTva(tva);
    }

    @Override
    public void deleteTva(Long id) {
        tvaSpiService.deleteById(id);

    }

    @Override
    public void updateTva(Tva tva) {
        tvaSpiService.updateTva(tva);

    }

    @Override
    public List<Tva> findBySiret(String siret) {
        return tvaSpiService.findBySiret(siret);
    }

    @Override
    public List<Tva> findByExerciseAndSiret(String exercise, String siret) {
        return tvaSpiService.findByExerciseAndSiret(exercise, siret);
    }

    @Override
    public Tva findById(Long id) {
        return tvaSpiService.findById(id);
    }


    @Override
    public void deleteByExercise(String exercise) {
        tvaSpiService.deleteByExercise(exercise);
    }

    @Override
    public TvaInfo findTvaInfo(String exercise, String siret) {
        return tvaSpiService.findTvaInfo(exercise, siret);
    }

}

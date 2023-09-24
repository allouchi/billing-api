package com.aliateck.fact.domaine.ports.api.tva;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.business.object.TvaInfo;

import java.util.List;

public interface TvaApiService {

    Tva addTva(Tva tva);

    void deleteTva(Long id);

    void updateTva(Tva tva);

    List<Tva> findByExerciseAndSiret(String exercise, String siret);

    Tva findById(Long id);

    void deleteByExercise(String exercise);

    List<Tva> findBySiret(String siret);

    TvaInfo findTvaInfo(String exercise, String siret);

}

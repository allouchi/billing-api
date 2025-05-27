package com.sbatec.fact.domaine.ports.spi.tva;

import com.sbatec.fact.domaine.business.object.Tva;
import com.sbatec.fact.domaine.business.object.TvaInfo;

import java.util.List;

public interface TvaSpiService {

    public void deleteByExercise(String exercise);

    public void deleteById(Long id);

    public List<Tva> findByExerciseAndSiret(String exercise, String siret);

    public Tva findById(Long id);

    public void updateTva(Tva tva);

    public Tva addTva(Tva tva);

    public List<Tva> findBySiret(String siret);

    public List<Tva> findByExercise(String exercise);

    public TvaInfo findTvaInfo(String exercise, String siret);
}

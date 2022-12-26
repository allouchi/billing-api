package com.aliateck.fact.domaine.ports.api.tva;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.business.object.TvaInfo;

public interface TvaApiService {

	 Tva addTva(Tva tva);

	 void deleteTva(Long id);

	 void updateTva(Tva tva);

	List<Tva> findByExercise(String exercise);

	 Tva findById(Long id);

	 void deleteByExercise(String exercise);

	 List<Tva> findAllTva();

	 TvaInfo findTvaInfo(String exercise);

}

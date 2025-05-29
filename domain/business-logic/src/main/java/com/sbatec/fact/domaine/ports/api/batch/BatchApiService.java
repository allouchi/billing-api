package com.sbatec.fact.domaine.ports.api.batch;

import java.util.List;

import com.sbatec.fact.domaine.business.object.Facture;

public interface BatchApiService {
	List<Facture> findAllFactures();
	
	public Facture calculerFraisRetard(Facture facture);
	
	public void updateFactures(Facture factures);

}

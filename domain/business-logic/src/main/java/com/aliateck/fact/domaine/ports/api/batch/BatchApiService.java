package com.aliateck.fact.domaine.ports.api.batch;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Facture;

public interface BatchApiService {
	List<Facture> findAllFactures();
	
	public Facture calculerFraisRetard(Facture facture);
	
	public void updateFactures(Facture factures);

}

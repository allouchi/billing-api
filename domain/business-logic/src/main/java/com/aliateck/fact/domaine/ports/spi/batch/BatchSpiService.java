package com.aliateck.fact.domaine.ports.spi.batch;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Facture;

public interface BatchSpiService {
	
	public Facture calculerFraisRetard(Facture facture);
	
	public List<Facture> findAllFactures();	
	
	public void updateFacture(Facture facture);

}

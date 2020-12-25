package com.aliateck.fact.domaine.common.edition;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public interface EditionFactureService {
	
	public Facture editerFacture(Company company, Prestation prestation, Facture facture);
}


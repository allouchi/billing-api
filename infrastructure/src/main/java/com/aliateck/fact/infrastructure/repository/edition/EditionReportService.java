package com.aliateck.fact.infrastructure.repository.edition;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public interface EditionReportService {
	
	public  byte[]  editerFacture(Company company, Prestation prestation, Facture facture);
}


package com.aliateck.fact.infrastructure.repository.edition;

import java.io.IOException;
import java.util.Map;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

import net.sf.jasperreports.engine.JRException;

public interface EditionReportService {
	public byte[] buildPdfFacture(Map<String, Object> paramJasper, boolean templateChoice, String path,
			boolean storeFile) throws JRException, IOException;

	public Map<String, Object> buildParamJasper(Company company, boolean templateChoice, Prestation prestation,
			Facture facture);
}

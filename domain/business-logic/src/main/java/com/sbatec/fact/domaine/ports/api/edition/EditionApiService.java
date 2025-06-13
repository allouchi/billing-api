package com.sbatec.fact.domaine.ports.api.edition;

import com.sbatec.fact.domaine.business.object.DataPDF;

public interface EditionApiService {

	public DataPDF downloadPdf(Long factureId);
}

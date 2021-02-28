package com.aliateck.fact.domaine.ports.api.edition;

import com.aliateck.fact.domaine.business.object.DataPDF;

public interface EditionApiService {

	public DataPDF downloadPdf(Long factureId);
}

package com.aliateck.fact.domaine.ports.spi.edition;

import com.aliateck.fact.domaine.business.object.DataPDF;

public interface EditionSpiService {

	public DataPDF downloadPdf(Long factureId);
}

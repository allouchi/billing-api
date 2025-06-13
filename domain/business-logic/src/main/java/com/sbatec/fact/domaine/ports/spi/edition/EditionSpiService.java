package com.sbatec.fact.domaine.ports.spi.edition;

import com.sbatec.fact.domaine.business.object.DataPDF;

public interface EditionSpiService {

	public DataPDF downloadPdf(Long factureId);
}

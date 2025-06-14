package com.sbatec.fact.domaine.adapter.edition;

import org.springframework.stereotype.Service;

import com.sbatec.fact.domaine.business.object.DataPDF;
import com.sbatec.fact.domaine.ports.api.edition.EditionApiService;
import com.sbatec.fact.domaine.ports.spi.edition.EditionSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionApiAdapter implements EditionApiService {
	EditionSpiService editionSpiService;

	@Override
	public DataPDF downloadPdf(Long factureId) {
		return editionSpiService.downloadPdf(factureId);
	}

}

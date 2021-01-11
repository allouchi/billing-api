package com.aliateck.fact.domaine.adapter.edition;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.ports.api.edition.EditionApiService;
import com.aliateck.fact.domaine.ports.spi.edition.EditionSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionApiAdapter implements EditionApiService {
	EditionSpiService editionSpiService;

	@Override
	public byte[] downloadPdf(String siret, Long prestationId, Long factureId, String rootDirectory) {
		return editionSpiService.downloadPdf(siret, prestationId, factureId, rootDirectory);
	}
	
}

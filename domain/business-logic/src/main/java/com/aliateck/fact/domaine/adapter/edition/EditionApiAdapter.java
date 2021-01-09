package com.aliateck.fact.domaine.adapter.edition;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
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
	public Facture buildFacture(String siret, long idPrestation, Facture facture) {
		return editionSpiService.buildFacture(siret, idPrestation, facture);
	}

	@Override
	public Facture editerFacture(String siret, long idPrestation, Facture facture) {
		return editionSpiService.editerFacture(siret, idPrestation, facture);
	}

	@Override
	public byte[] downloadPdf(String path) {
		return editionSpiService.downloadPdf(path);
	}
}

package com.aliateck.fact.domaine.ports.spi.edition;

import com.aliateck.fact.domaine.business.object.Facture;

public interface EditionSpiService {
	
	public Facture editerFacture(String siret, Long prestationId, Facture facture, String pathRoot);

	public byte[] downloadPdf(String siret, Long prestationId, Long factureId, String rootDirectory);
}

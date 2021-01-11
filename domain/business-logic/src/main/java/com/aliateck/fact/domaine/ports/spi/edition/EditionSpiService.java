package com.aliateck.fact.domaine.ports.spi.edition;

public interface EditionSpiService {
	
	public byte[] downloadPdf(String siret, Long prestationId, Long factureId, String rootDirectory);
}

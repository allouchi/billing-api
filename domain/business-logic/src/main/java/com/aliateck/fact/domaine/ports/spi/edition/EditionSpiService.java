package com.aliateck.fact.domaine.ports.spi.edition;

public interface EditionSpiService {
	
	public byte[] editerFacture(String siret, long prestationId, long factureId);
}


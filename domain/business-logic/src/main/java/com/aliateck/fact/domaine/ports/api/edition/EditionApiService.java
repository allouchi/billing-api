package com.aliateck.fact.domaine.ports.api.edition;

public interface EditionApiService {

	public byte[] downloadPdf(String siret, Long idPrestation, Long factureId, String rootDirectory);
}

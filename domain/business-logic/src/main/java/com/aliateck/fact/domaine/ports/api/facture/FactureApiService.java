package com.aliateck.fact.domaine.ports.api.facture;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Facture;

public interface FactureApiService {
	public Facture addFacture(String siret, Facture factureRequest, Long prestationId, String pathRoot);

	public void deleteFacture(Long factureId);	

	public Facture updateFacture(Facture facture);

	public Facture findById(Long id);

	public Facture findByNumero(String numero);	

	public List<Facture> findAllBySiret(String siret);
}

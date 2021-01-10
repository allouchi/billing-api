package com.aliateck.fact.domaine.ports.api.facture;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Facture;

public interface FactureApiService {
	public Facture addFacture(String siret, Facture factureRequest, Long prestationId);

	public void deleteFacture(String siret, Facture facture, Long prestationId);

	public void deleteById(String siret, Long idPrestation, Long idFacture);

	public Facture updateFacture(String siret, Facture facture, Long prestationId);

	public Facture findById(Long id);

	public Facture findByNumero(String numero);

	public List<Facture> findAllByPrestation(String siret, Long idPrestation);

	public List<Facture> findAllBySiret(String siret);
}

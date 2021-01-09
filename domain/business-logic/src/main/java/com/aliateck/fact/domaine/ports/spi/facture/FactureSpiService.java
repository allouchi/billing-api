package com.aliateck.fact.domaine.ports.spi.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface FactureSpiService {
	Facture addFacture(String siret, Facture facture, Long prestationId);

	void deleteFacture(String siret, Facture facture, Long prestationId);

	void deleteFactureById(String siret, Long idPrestation, Long idFacture);

	Facture updateFacture(String siret, Facture facture, Long prestationId);

	Facture findByNumeroFacture(String numeroFacture);

	Facture findById(Long id);

	List<Facture> findByFactureStatus(boolean statusFacture);

	List<Facture> findByDateEcheance(Date dateEcheance);

	List<Facture> findByDateEncaissement(Date dateEncaissement);

	List<Facture> findAllByPrestation(String siret, Long idPrestation);

	List<Facture> findAllBySiret(String siret);
}

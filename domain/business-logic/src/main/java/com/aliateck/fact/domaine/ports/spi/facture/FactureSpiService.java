package com.aliateck.fact.domaine.ports.spi.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import java.util.Date;
import java.util.List;

public interface FactureSpiService {
  Facture addFacture(Facture facture, long prestationId, String numeroCommande);

  void deleteFacture(Facture facture);
  
  void deleteById(long id);

  Facture updateFacture(Facture facture);

  Facture findByNumeroFacture(String numeroFacture);

  Facture findById(long id);

  List<Facture> findByFactureStatus(boolean statusFacture);

  List<Facture> findByDateEcheance(Date dateEcheance);

  List<Facture> findByDateEncaissement(Date dateEncaissement);

  List<Facture> findAllByPrestation(String siret, long idPrestation);

  List<Facture> findAllBySiret(String siret);
}

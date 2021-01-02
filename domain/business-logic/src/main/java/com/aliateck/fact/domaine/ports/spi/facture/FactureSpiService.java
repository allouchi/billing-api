package com.aliateck.fact.domaine.ports.spi.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import java.util.Date;
import java.util.List;

public interface FactureSpiService {
  Facture addFacture(String siret, Facture facture, long prestationId);

  void deleteFacture(String siret, Facture facture, long prestationId);
  
  void deleteById(String siret, long idPrestation, long idFacture);

  Facture updateFacture(String siret, Facture facture, long prestationId);

  Facture findByNumeroFacture(String numeroFacture);

  Facture findById(long id);

  List<Facture> findByFactureStatus(boolean statusFacture);

  List<Facture> findByDateEcheance(Date dateEcheance);

  List<Facture> findByDateEncaissement(Date dateEncaissement);

  List<Facture> findAllByPrestation(String siret, long idPrestation);

  List<Facture> findAllBySiret(String siret);
}

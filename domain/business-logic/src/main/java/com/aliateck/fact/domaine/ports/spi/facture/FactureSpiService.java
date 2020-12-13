package com.aliateck.fact.domaine.ports.spi.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import java.util.Date;
import java.util.List;

public interface FactureSpiService {
  void addFacture(Facture facture);

  void deleteFacture(Facture facture);

  void updateFacture(Facture facture);

  Facture findByNumeroFacture(String numeroFacture);

  Facture findById(long id);

  List<Facture> findByFactureStatus(boolean statusFacture);

  List<Facture> findByDateEcheance(Date dateEcheance);

  List<Facture> findByDateEncaissement(Date dateEncaissement);

  List<Facture> findBySiret(String siret);

  List<Facture> findAll();
}

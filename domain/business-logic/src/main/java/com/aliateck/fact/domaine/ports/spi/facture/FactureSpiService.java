package com.aliateck.fact.domaine.ports.spi.facture;

import java.util.Date;
import java.util.List;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public interface FactureSpiService {	
	
  Prestation addFacture(String siret, boolean templateChoice, Prestation prestation,
      String pathRoot, Long moisFactureId, boolean storeFile, String fileSuivi);

  void deleteFacture(Long factureId);

  Facture updateFacture(Facture facture, String rootPath, String fileSuiviName);

  Facture findByNumeroFacture(String numeroFacture);

  Facture findById(Long id);

  List<Facture> findByFactureStatus(boolean statusFacture);

  List<Facture> findByDateEcheance(Date dateEcheance);

  List<Facture> findByDateEncaissement(Date dateEncaissement);

  List<Facture> findAllBySiret(String siret);
}

package com.aliateck.fact.domaine.ports.spi.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

import java.util.List;

public interface FactureSpiService {

    Prestation addFacture(String siret, Boolean templateChoice, Prestation prestation,
                          String pathRoot, Long moisFactureId, Boolean storeFile, String fileSuivi);

    void deleteFacture(Long factureId);

    Facture updateFacture(Facture facture, String rootPath, String fileSuiviName);

    Facture findByNumeroFacture(String numeroFacture);

    Facture findById(Long id);

    List<Facture> findAllBySiret(String siret);
}

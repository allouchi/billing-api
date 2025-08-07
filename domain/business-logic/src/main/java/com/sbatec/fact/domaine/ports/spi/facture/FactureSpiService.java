package com.sbatec.fact.domaine.ports.spi.facture;

import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.business.object.Prestation;

import java.util.List;

public interface FactureSpiService {

    Prestation addFacture(String siret, boolean isTextGeneration, Prestation prestation,
                          String pathRoot, Long moisFactureId, Boolean storeFile, String fileSuivi);

    void deleteFacture(Long factureId);

    Facture updateFacture(Facture facture, String rootPath, String fileSuiviName);

    Facture findByNumeroFacture(String numeroFacture);

    Facture findById(Long id);

    List<Facture> findAllBySiret(String siret);

    List<Facture> findAllByExercice(String siret, String exercice);
}

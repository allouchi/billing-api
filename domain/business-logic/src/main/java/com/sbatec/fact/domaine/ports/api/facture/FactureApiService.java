package com.sbatec.fact.domaine.ports.api.facture;

import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.business.object.Prestation;

import java.util.List;

public interface FactureApiService {

    Prestation addFacture(String siret, boolean templateChoice, Prestation prestation,
                          String pathRoot, Long moisFactureId, boolean storeFile, String fileSuivi);

    void deleteFacture(Long factureId);

    Facture updateFacture(Facture facture, String rootPath, String fileSuiviName);

    Facture findById(Long id);

    Facture findByNumero(String numero);

    List<Facture> findFacturesBySiret(String siret);

    List<Facture> findFacturesByExercice(String siret, String exercice);
}

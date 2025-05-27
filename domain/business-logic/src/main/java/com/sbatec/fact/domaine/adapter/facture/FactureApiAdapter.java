package com.sbatec.fact.domaine.adapter.facture;

import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.business.object.Prestation;
import com.sbatec.fact.domaine.ports.api.facture.FactureApiService;
import com.sbatec.fact.domaine.ports.spi.facture.FactureSpiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureApiAdapter implements FactureApiService {
    FactureSpiService factureSpiService;

    @Override
    public Prestation addFacture(String siret, boolean templateChoice, Prestation prestation,
                                 String pathRoot, Long moisFactureId, boolean storeFile, String fileSuivi) {
        return factureSpiService.addFacture(siret, templateChoice, prestation, pathRoot, moisFactureId,
                storeFile, fileSuivi);
    }

    @Override
    public void deleteFacture(Long factureId) {
        factureSpiService.deleteFacture(factureId);
    }

    @Override
    public Facture updateFacture(Facture facture, String rootPath, String suiviFileName) {
        return factureSpiService.updateFacture(facture, rootPath, suiviFileName);
    }

    @Override
    public Facture findById(Long id) {
        return factureSpiService.findById(id);
    }

    @Override
    public Facture findByNumero(String numeroFacture) {
        return factureSpiService.findByNumeroFacture(numeroFacture);
    }

    @Override
    public List<Facture> findFacturesBySiret(String siret) {
        return factureSpiService.findAllBySiret(siret);
    }

}

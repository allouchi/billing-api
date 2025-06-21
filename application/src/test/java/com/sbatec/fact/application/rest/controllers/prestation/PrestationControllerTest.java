package com.sbatec.fact.application.rest.controllers.prestation;

import com.sbatec.fact.domaine.business.object.Prestation;
import com.sbatec.fact.domaine.ports.api.facture.FactureApiService;
import com.sbatec.fact.domaine.ports.api.prestation.PrestationApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PrestationControllerTest {

    @Autowired
    FactureApiService factureApiService;
    @Autowired
    PrestationApiService prestationApiService;

    private Prestation prestation;

    @BeforeEach
    void setUp() {
        prestation = prestationApiService.findById(5L);

    }

    @Test
    void createFacture() {
        String siret = "85292702900011";
        String path = "c:/temp/pdf";
        prestation.setQuantite(20f);
        Prestation edited = factureApiService.addFacture(siret, true, prestation, path, 1L, false, null);
        System.out.println(edited);
    }
}
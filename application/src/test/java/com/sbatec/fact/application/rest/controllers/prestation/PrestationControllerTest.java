package com.sbatec.fact.application.rest.controllers.prestation;

import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.business.object.Prestation;
import com.sbatec.fact.domaine.ports.api.facture.FactureApiService;
import com.sbatec.fact.domaine.ports.api.prestation.PrestationApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PrestationControllerTest {

    @Autowired
    FactureApiService factureApiService;
    @Autowired
    PrestationApiService prestationApiService;

    private Facture facture;
    private Prestation prestation;

    @BeforeEach
    void setUp() {
        prestation = prestationApiService.findById(5L);
        facture = factureApiService.findById(60l);
    }

    @Test
    void createFacture() {
        String siret ="85292702900011";
        String path ="c:/temp/pdf";
        Prestation edited = factureApiService.addFacture(siret, false, prestation, path, 1L, false, null);
        System.out.println(edited);
    }
}
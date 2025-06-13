package com.sbatec.fact.infrastructure.repository.edition;

import com.lowagie.text.DocumentException;
import com.sbatec.fact.domaine.business.object.Company;
import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.business.object.Prestation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface EditionReportService {
    byte[] buildPdfFacture(Map<String, Object> paramJasper,
                           String path, boolean storeFile) throws IOException;

    Map<String, Object> buildParamJasper(Company company,
                                         Prestation prestation, Facture facture);

    void buildSuiviFactures(List<Facture> factures, String path);

    Map<String, Object> buildParamsNewTemplate(Company company,
                                               Prestation prestation, Facture facture);

    byte[] buildFacturePdFSaucer(Map<String, Object> paramJasper,
                                 String path, boolean storeFile) throws IOException, URISyntaxException, DocumentException;
}

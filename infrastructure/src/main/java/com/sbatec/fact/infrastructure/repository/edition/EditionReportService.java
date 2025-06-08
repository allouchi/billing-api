package com.sbatec.fact.infrastructure.repository.edition;

import com.sbatec.fact.domaine.business.object.Company;
import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.business.object.Prestation;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EditionReportService {
    byte[] buildPdfFacture(Map<String, Object> paramJasper, boolean templateChoice,
                           String path, boolean storeFile) throws JRException, IOException;

    Map<String, Object> buildParamJasper(Company company, boolean templateChoice,
                                         Prestation prestation, Facture facture);

    void buildSuiviFactures(List<Facture> factures, String path);

    byte[] buildFacturePdfItext(Map<String, Object> data, boolean templateChoice,
                                String path, boolean storeFile) throws IOException;
}

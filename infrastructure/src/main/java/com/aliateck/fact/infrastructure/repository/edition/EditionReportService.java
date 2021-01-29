package com.aliateck.fact.infrastructure.repository.edition;

import java.util.Map;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public interface EditionReportService {
  public void buildPdfFacture(
    Map<String, Object> paramJasper, boolean templateChoice,
    String path      
  );

  public Map<String, Object> buildParamJasper(
    Company company, boolean templateChoice,
    Prestation prestation,
    Facture facture
  );
}

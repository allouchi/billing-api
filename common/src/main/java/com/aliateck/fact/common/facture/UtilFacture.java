package com.aliateck.fact.common.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilFacture {

  private UtilFacture() {}

  public static Facture calculerFacture(Prestation prestation) {
    float tarifHT = prestation.getTarif();

    float montantHT = tarifHT * prestation.getNbJoursEffectue();
    float tva = montantHT * 0.2f;
    prestation.getFacture().setPrixTotalHT(montantHT);
    prestation.getFacture().setPrixTotalTTC(montantHT + tva);
    prestation.getFacture().setTva(tva);
    prestation.getFacture().setTarifHT(tarifHT);
    prestation.getFacture().setDateFacturation(convertToDate(LocalDate.now()));
    prestation.getFacture().setDateEcheance(calculerDateEcheance(prestation));

    return prestation.getFacture();
  }

  private static String calculerDateEcheance(Prestation prestation) {
    long delai = prestation.getDelaiPaiement();
    LocalDate dateEcheance = LocalDate.now().plusDays(delai);
    return convertToDate(dateEcheance);
  }

  public static String convertToDate(LocalDate dateToConvert) {
    final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    return formaterDate.format(dateToConvert);
  }
}

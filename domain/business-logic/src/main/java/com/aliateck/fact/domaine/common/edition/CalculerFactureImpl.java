package com.aliateck.fact.domaine.common.edition;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.FactureStatus;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;


import org.springframework.stereotype.Service;

@Service
public class CalculerFactureImpl implements CalculerFactureService {
	
	
  
  /*
   *
   */
  @Override
  public Facture calculerFacture(Prestation prestation) {
	
	Facture facture = new Facture();
    float tarifHT = prestation.getTarifHT();   
    float prixTotalHT = tarifHT * facture.getQuantite();
    float tva = prixTotalHT * 0.2f;
    
    facture.setPrixTotalHT(prixTotalHT);
    facture.setPrixTotalTTC(prixTotalHT + tva);
    facture.setMontantTVA(tva);
    facture.setDelaiPaiement(prestation.getDelaiPaiement());
    facture.setDateFacturation(convertToDate(LocalDate.now()));
    facture.setDateEcheance(calculerDateEcheance(prestation));
    long nbJourRetard = calculerNbJourRetard(facture);
    facture.setNbJourRetard(nbJourRetard);
    facture.setFraisRetard(calculerFraisRetard(facture));
    facture.setMoisFacture(determinerMoisFacture());
    facture.setNumeroFacture(calulerNumeroFacture(000));
    facture.setFactureStatus(FactureStatus.NON.getCode());
    return facture;
  }

  /*
   *
   */
  private static long calculerNbJourRetard(Facture facture) {
    LocalDate dateEcheance = LocalDate.now().plusDays(facture.getDelaiPaiement());
    LocalDate dateJour = LocalDate.now();
    if (Period.between(dateEcheance, dateJour).getDays() > 0) {
      return Period.between(dateEcheance, dateJour).getDays();
    }
    return 0;
  }

  /*
   *
   */
  private static Float calculerFraisRetard(Facture facture) {
    if (facture.getNbJourRetard() > 0) {
      float div = (float) facture.getNbJourRetard() / 365;
      return 2.52f * facture.getPrixTotalHT() * div;
    }
    return 0f;
  }

  /*
   *
   */
  private static String calculerDateEcheance(Prestation prestation) {
    long delai = prestation.getDelaiPaiement();
    LocalDate dateEcheance = LocalDate.now().plusDays(delai);
    return convertToDate(dateEcheance);
  }

  /*
   *
   */
  public static String convertToDate(LocalDate dateToConvert) {
    final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return formaterDate.format(dateToConvert);
  }

  /*
   *
   */
  public static String determinerMoisFacture() {
    Month mois = LocalDate.now().getMonth();
    String formatMois = mois.getDisplayName(TextStyle.FULL, Locale.FRENCH);
    formatMois =
      formatMois.substring(0, 1).toUpperCase() +
      formatMois.substring(1, formatMois.length());
    return formatMois;
  }

  /*
   *
   */
  public static String calulerNumeroFacture(long id) {
    final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate dateJour = LocalDate.now();
    return formaterDate.format(dateJour) + "-100" + id;
  }
}

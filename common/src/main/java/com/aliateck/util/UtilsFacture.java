package com.aliateck.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.util.ResourceUtils;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public class UtilsFacture {

  private UtilsFacture() {}

  /*
   *
   */
  public static long calculerNbJourRetard(Facture facture) {
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
  public static Float calculerFraisRetard(Facture facture) {
    if (facture.getNbJourRetard() > 0) {
      float div = (float) facture.getNbJourRetard() / 365;
      return 2.52f * facture.getPrixTotalHT() * div;
    }
    return 0f;
  }

  /*
   *
   */
  public static String calculerDateEcheance(Prestation prestation) {
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
  public static String calculerNumeroFacture(long id) {
    final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate dateJour = LocalDate.now();
    return formaterDate.format(dateJour) + "-100" + id;
  }
  
  /*
  *
  */
 public static String updateNumeroFacture(String numeroFacture, long id) {
	 
	 String endNumero[] = numeroFacture.split("-");
		long oldNumero = Long.parseLong(endNumero[1]);
		long newNumero = oldNumero + id;
		return String.valueOf(endNumero[0]+"-"+newNumero);	
 }
  
  
  public static File loadJasperFile() throws FileNotFoundException {
		    return ResourceUtils.getFile(
		      "classpath:data/factureDesign.jrxml");
	}
}

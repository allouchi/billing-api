package com.aliateck.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.springframework.core.io.ClassPathResource;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public class Utils {
  
  private static Map<String, String> mapMois = new HashMap<>();
  private static final String TIRET="-";

  static {

    mapMois.put("1", "Janvier");
    mapMois.put("2", "Février");
    mapMois.put("3", "Mars");
    mapMois.put("4", "Avril");
    mapMois.put("5", "Mai");
    mapMois.put("6", "Juin");
    mapMois.put("7", "Juillet");
    mapMois.put("8", "Août");
    mapMois.put("9", "Septembre");
    mapMois.put("10", "Octobre");
    mapMois.put("11", "Novembre");
    mapMois.put("12", "Décembre");
  }

  private Utils() {}

  public static boolean isEmpty() {
    return true;
  }

  public static Consultant formatConsulantName(Consultant consultant) {

    consultant.setLastName(consultant.getLastName().toUpperCase());
    String firstName = consultant.getFirstName().substring(0, 1).toUpperCase()
        + consultant.getFirstName().substring(1, consultant.getFirstName().length());
    consultant.setFirstName(firstName);
    return consultant;
  }

  /*
   *
   */
  public static String convertMoisFacture(String moisId) {
    String mois = "";
    if (mapMois.containsKey(moisId)) {
      mois = mapMois.get(moisId);
    }
    return mois;
  }

  /*
   *
   */
  public static String buildMoisFacture(String mois) {

    String[] moisId = new String[1];

    mapMois.forEach((key, value) -> {
      if (value.equalsIgnoreCase(mois)) {
        moisId[0] = key;
      }
    });

    int anneeCourante = LocalDate.now().getYear();
    LocalDate date = LocalDate.of(anneeCourante, Integer.parseInt(moisId[0]), 1);
    String[] stringDate = date.toString().split("-");
    return stringDate[0] + stringDate[1];
  }

  /*
   *
   */
  public static Map<String, File> loadFilesResources() throws IOException {
    Map<String, File> map = new HashMap<>();

    File customFile = new ClassPathResource("data/customTemplate.jrxml").getFile();
    File defaultFile = new ClassPathResource("data/defaultTemplate.jrxml").getFile();
    File excelFile = new ClassPathResource("data/suivi-facturation.xls").getFile();
    map.put("Default", defaultFile);
    map.put("Custom", customFile);
    map.put("Suivi", excelFile);
    return map;
  }

  /*
   *
   */

  public static Facture updateFacture(Facture oFacture, Facture factureRequest) {

    if (oFacture == null || factureRequest == null) {
      return null;
    }

    if (factureRequest.getDateEncaissement() != null
        && !factureRequest.getDateEncaissement().isEmpty()) {
      String dateEncaissement =
          Utils.convertFromDomainToEntityDate(factureRequest.getDateEncaissement());
      oFacture.setDateEncaissement(dateEncaissement);
      oFacture.setFactureStatus(FactureStatus.OUI.getCode());
      oFacture.setStatusDesc(FactureStatus.OUI.getDescription());
      oFacture.setFraisRetard(0);
      oFacture.setNbJourRetard(0);
    }
    return oFacture;

  }

  public static LocalDate convertStringToDate(String dateToConvert) {
    if (dateToConvert == null) {
      return null;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return LocalDate.parse(dateToConvert, formatter);
  }

  public static long calculerNbJourRetard(Facture facture) {
    if (facture == null) {
      return 0;
    }
    if (facture.getFactureStatus().equalsIgnoreCase(FactureStatus.NON.getCode())) {
      LocalDate dateEcheance = convertStringToDate(facture.getDateEcheance());
      LocalDate dateJour = LocalDate.now();
      if (Period.between(dateEcheance, dateJour).getDays() > 0) {
        return ChronoUnit.DAYS.between(dateEcheance, dateJour);
      }
    }
    return 0;
  }

  /*
   *
   */
  public static float calculerFraisRetard(Facture facture, long joursRetard) {
    if (facture == null) {
      return 0;
    }
    if (facture.getFactureStatus().equalsIgnoreCase(FactureStatus.NON.getCode())) {
      float div = (float) joursRetard / 365;
      float cal = (0.1f * facture.getPrixTotalTTC() * div) + 40;
      System.out.println("********************************");
      System.out.println(cal);
      System.out.println("********************************");
      // String fraisRetard = String.format("%.2f", cal);
      return cal;

    }
    return 0f;
  }

  /*
   *
   */
  public static String calculerDateEcheance(Prestation prestation, String moisFacture) {
    
    if (prestation == null) {
      return null;
    }
    
    String[] moisId = new String[1];

    mapMois.forEach((key, value) -> {
      if (value.equalsIgnoreCase(moisFacture)) {
        moisId[0] = key;
      }
    });
    
    long delai = prestation.getDelaiPaiement();
    LocalDate dateActuelle = LocalDate.now();   
    String dateFacture = dateActuelle.getYear() +TIRET+  moisId[0]  + TIRET +"01";
    LocalDate date = LocalDate.parse(dateFacture);
    LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());
    LocalDate dateEcheance = endOfMonth.plusDays(delai);
    return convertToDateFromLocalDate(dateEcheance);
  }

  /*
   *
   */
  public static String convertToDateFromLocalDate(LocalDate dateToConvert) {
    if (dateToConvert == null) {
      return null;
    }
    final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return formaterDate.format(dateToConvert);
  }

  /*
   *
   */
  public static String convertFromDomainToEntityDate(String dateToConvert) {
    if (dateToConvert == null || dateToConvert.isEmpty()) {
      return null;
    }
    String tab[] = dateToConvert.split("-");
    return tab[2] + "/" + tab[1] + "/" + tab[0];
  }

  /*
   *
   */
  public static String convertFromEntityToDomainDate(String dateToConvert) {
    if (dateToConvert == null || dateToConvert.isEmpty()) {
      return null;
    }

    String tab[] = dateToConvert.split("/");
    return tab[2] + "-" + tab[1] + "-" + tab[0];
  }

  /*
   *
   */
  public static byte[] encodeToBase64(byte[] inputFile) {
    Base64.Encoder encoder = Base64.getEncoder();
    return encoder.encode(inputFile);

  }

  /*
   *
   */
  public static String determinerMoisFacture() {
    Month mois = LocalDate.now().getMonth();
    mois = mois.minus(1);
    String formatMois = mois.getDisplayName(TextStyle.FULL, Locale.FRENCH);
    formatMois =
        formatMois.substring(0, 1).toUpperCase() + formatMois.substring(1, formatMois.length());
    return formatMois;
  }

  /*
  *
  */
  public static String updateNumeroFacture(String rsClient, List<Facture> listeFactures, Long moisId) {

    Set<Integer> numeros = new HashSet<>();
    
    String numeroFacture = null;
    if (listeFactures == null || listeFactures.isEmpty()) {
      return Utils.buildNumeroFacture("1000", moisId);
    }
    numeros.add(1000);
    
    for (Facture facture : listeFactures) {
      String client = facture.getClientPrestation();     
      if (rsClient != null && client != null &&  client.toLowerCase().equals(rsClient) ) {
        numeroFacture = facture.getNumeroFacture();
        String endNumero[] = numeroFacture.split("-");
        Integer numero = Integer.parseInt(endNumero[1]);
        numeros.add(numero);
      }
    }
    int max = Collections.max(numeros);
    return Utils.buildNumeroFacture(String.valueOf(max + 1), moisId);
  }

  /*
   *
   */
  public static String buildNumeroFacture(String endNumero, Long moisFacture) {
    
   
    
    LocalDate dateActuelle = LocalDate.now();   
    String dateFacture = dateActuelle.getYear() +TIRET+ moisFacture  + TIRET +"01";
    LocalDate date = LocalDate.parse(dateFacture);
    LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());    
    String dateConvert = convertToDateFromLocalDate(endOfMonth);
    return dateConvert + "-" + endNumero;
  }

  /*
   *
   */
  public static String buildPath(String pathComplet, String rootPath) {
    String path = null;
    if (pathComplet != null && rootPath != null) {
      int lg = rootPath.length();
      path = pathComplet.substring(lg, pathComplet.length());
    }
    return path;
  }

  /*
  *
  */
  public static String buildPathSuivi(String pathComplet, String fileName) {
    String path = null;
    int anneeCourante = LocalDate.now().getYear();
    path = pathComplet + File.separator + anneeCourante + File.separator + fileName;

    return path;
  }

  /*
  *
  */
  public static String getSiretFromPath(String pathComplet) {
    String sub = pathComplet.substring(1);
    String[] url = sub.replace("\\", "_").split("_");
    return url[0];
  }

  /**
   * nombre de jours entre 2 dates
   * 
   * @param d1
   * @param d2
   * @param notionJourFerie prendre en compte les jours fériés
   * @param priseCompteLundi prendre en compte les Lundi
   * @param priseCompteMardi prendre en compte les Mardi
   * @param priseCompteMercredi prendre en compte les Mercredi
   * @param priseCompteJeudi prendre en compte les Jeudi
   * @param priseCompteVendredi prendre en compte Vendredi
   * @param priseCompteSamedi prendre en compte les Samedi
   * @param priseCompteDimanche prendre en compte les Dimanche
   * @return
   */
  public static int nbJours(Date d1, Date d2, boolean notionJourFerie, boolean priseCompteLundi,
      boolean priseCompteMardi, boolean priseCompteMercredi, boolean priseCompteJeudi,
      boolean priseCompteVendredi, boolean priseCompteSamedi, boolean priseCompteDimanche) {

    if (d2.compareTo(d1) <= 0)
      return 0;

    // Tableau des jours a prendre en compte
    Boolean[] joursPrisEncompte =
        new Boolean[] {priseCompteDimanche, priseCompteLundi, priseCompteMardi, priseCompteMercredi,
            priseCompteJeudi, priseCompteVendredi, priseCompteSamedi};

    GregorianCalendar date1 = new GregorianCalendar();
    date1.setTime(d1);
    GregorianCalendar date2 = new GregorianCalendar();
    date2.setTime(d2);

    // Récupération des jours fériés
    List<Date> joursFeries = new ArrayList<>();
    for (int i = date1.get(GregorianCalendar.YEAR); i <= date2.get(GregorianCalendar.YEAR); i++) {
      joursFeries.addAll(getJourFeries(i));
    }

    // Calcul du nombre de jour
    int nbJour = 0;
    while (date1.before(date2) || date1.equals(date2)) {
      if (!notionJourFerie || !joursFeries.contains(date1.getTime())) {
        if (joursPrisEncompte[date1.get(GregorianCalendar.DAY_OF_WEEK) - 1] != null)
          nbJour++;
      }

      date1.add(GregorianCalendar.DAY_OF_MONTH, 1);
    }

    return nbJour;
  }

  /**
   * retourne une liste de jours fériés pour une année
   * 
   * @param annee
   * @return
   */
  private static List<Date> getJourFeries(int annee) {
    List<Date> datesFeries = new ArrayList<>();

    // Jour de l'an
    GregorianCalendar jourAn = new GregorianCalendar(annee, 0, 1);
    datesFeries.add(jourAn.getTime());

    // Lundi de pacques
    GregorianCalendar pacques = calculLundiPacques(annee);
    datesFeries.add(pacques.getTime());

    // Fete du travail
    GregorianCalendar premierMai = new GregorianCalendar(annee, 4, 1);
    datesFeries.add(premierMai.getTime());

    // 8 mai
    GregorianCalendar huitMai = new GregorianCalendar(annee, 4, 8);
    datesFeries.add(huitMai.getTime());

    // Ascension (= pâques + 38 jours)
    GregorianCalendar ascension = new GregorianCalendar(annee, pacques.get(GregorianCalendar.MONTH),
        pacques.get(GregorianCalendar.DAY_OF_MONTH));
    ascension.add(GregorianCalendar.DAY_OF_MONTH, 38);
    datesFeries.add(ascension.getTime());

    // Pentecôte (= pâques + 49 jours)
    GregorianCalendar pentecote = new GregorianCalendar(annee, pacques.get(GregorianCalendar.MONTH),
        pacques.get(GregorianCalendar.DAY_OF_MONTH));
    pentecote.add(GregorianCalendar.DAY_OF_MONTH, 49);
    datesFeries.add(pentecote.getTime());

    // Fête Nationale
    GregorianCalendar quatorzeJuillet = new GregorianCalendar(annee, 6, 14);
    datesFeries.add(quatorzeJuillet.getTime());

    // Assomption
    GregorianCalendar assomption = new GregorianCalendar(annee, 7, 15);
    datesFeries.add(assomption.getTime());

    // La Toussaint
    GregorianCalendar toussaint = new GregorianCalendar(annee, 10, 1);
    datesFeries.add(toussaint.getTime());

    // L'Armistice
    GregorianCalendar armistice = new GregorianCalendar(annee, 10, 11);
    datesFeries.add(armistice.getTime());

    // Noël
    GregorianCalendar noel = new GregorianCalendar(annee, 11, 25);
    datesFeries.add(noel.getTime());

    return datesFeries;
  }

  public static GregorianCalendar calculLundiPacques(int annee) {
    int a = annee / 100;
    int b = annee % 100;
    int c = (3 * (a + 25)) / 4;
    int d = (3 * (a + 25)) % 4;
    int e = (8 * (a + 11)) / 25;
    int f = (5 * a + b) % 19;
    int g = (19 * f + c - e) % 30;
    int h = (f + 11 * g) / 319;
    int j = (60 * (5 - d) + b) / 4;
    int k = (60 * (5 - d) + b) % 4;
    int m = (2 * j - k - g + h) % 7;
    int n = (g - h + m + 114) / 31;
    int p = (g - h + m + 114) % 31;
    int jour = p + 1;
    int mois = n;

    GregorianCalendar date = new GregorianCalendar(annee, mois - 1, jour);
    date.add(GregorianCalendar.DAY_OF_MONTH, 1);
    return date;
  }

}

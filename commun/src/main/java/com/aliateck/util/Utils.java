package com.aliateck.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
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

	static Map<String, File> map = null;
	private static Map<String, String> mapMois = new HashMap<>();
	private static final String TIRET = "-";
	private static final String SLATSH = "/";
	private String custTemplate = "data/customTemplate.jrxml";
	private String custDefaultTemplate = "data/defaultTemplate.jrxml";
	private String suiviFacturation = "data/suivi-facturation.xls";

	static {

		mapMois.put("01", "Janvier");
		mapMois.put("02", "Février");
		mapMois.put("03", "Mars");
		mapMois.put("04", "Avril");
		mapMois.put("05", "Mai");
		mapMois.put("06", "Juin");
		mapMois.put("07", "Juillet");
		mapMois.put("08", "Août");
		mapMois.put("09", "Septembre");
		mapMois.put("10", "Octobre");
		mapMois.put("11", "Novembre");
		mapMois.put("12", "Décembre");

	}

	private Utils()  {
		
	}

	/**
	 * 
	 * @param consultant
	 * @return
	 */
	public static Consultant formatConsulantName(Consultant consultant) {

		consultant.setLastName(consultant.getLastName().toUpperCase());
		String firstName = consultant.getFirstName().substring(0, 1).toUpperCase()
				+ consultant.getFirstName().substring(1, consultant.getFirstName().length());
		consultant.setFirstName(firstName);
		return consultant;
	}

	/**
	 * 
	 * @param moisId
	 * @return
	 */
	public static String convertMoisFacture(String moisId) {
		String mois = "";
		if (mapMois.containsKey(moisId)) {
			mois = mapMois.get(moisId);
		}
		return mois;
	}

	/**
	 * 	
	 */
	public static List<String> makeExercises() {
		List<String> excercises = new ArrayList<>();
		excercises.add("2001-2022");
		excercises.add("2002-2023");
		excercises.add("2003-2024");
		excercises.add("2004-2025");
		LocalDate dateActuelle = LocalDate.now();
		Month mois = dateActuelle.getMonth();
		if (mois.getValue() == 11) {
			int annee = dateActuelle.getYear();
			int anneeSuivante = annee + 1;
			excercises.add(annee + "-" + anneeSuivante);
		}

		return excercises;
	}

	/**
	 * 
	 * @param mois
	 * @return
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

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	public static Map<String, File> loadFilesResources() throws IOException, URISyntaxException {
		Map<String, File> map = new HashMap<>();
		 String custTemplate = "data/customTemplate.jrxml";
		 String custDefaultTemplate = "data/defaultTemplate.jrxml";
		 String suiviFacturation = "data/suivi-facturation.xls";
		
		 File customFile = new ClassPathResource(custTemplate).getFile();
		 File defaultFile = new ClassPathResource(custDefaultTemplate).getFile();
		 File excelFile = new ClassPathResource(suiviFacturation).getFile();
		 map.put("Default", defaultFile);
		 map.put("Custom", customFile);
		 map.put("Suivi", excelFile);
		//LoadRessources load = new LoadRessources();
		//map = load.load();
		return map;
	}

	/**
	 * 
	 * @param oFacture
	 * @param factureRequest
	 * @return
	 */
	public static Facture updateFacture(Facture oFacture, Facture factureRequest) {

		if (oFacture == null || factureRequest == null) {
			return null;
		}

		if (factureRequest.getDateEncaissement() != null && !factureRequest.getDateEncaissement().isEmpty()) {
			String dateEncaissement = Utils.convertFromDomainToEntityDate(factureRequest.getDateEncaissement());
			oFacture.setDateEncaissement(dateEncaissement);
			oFacture.setFactureStatus(FactureStatus.OUI.getCode());
			oFacture.setStatusDesc(FactureStatus.OUI.getDescription());
			oFacture.setFraisRetard(0);
			oFacture.setNbJourRetard(0);
			oFacture.setMontantTVA(factureRequest.getPrixTotalHT() * 0.2f);
		}
		return oFacture;

	}

	/**
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public static LocalDate convertStringToDate(String dateToConvert) {
		if (dateToConvert == null) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(dateToConvert, formatter);
	}

	/**
	 * 
	 * @param facture
	 * @return
	 */
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

	/**
	 * 
	 * @param facture
	 * @param joursRetard
	 * @return
	 */
	public static float calculerFraisRetard(Facture facture, long joursRetard) {
		if (facture == null) {
			return 0;
		}
		if (facture.getFactureStatus().equalsIgnoreCase(FactureStatus.NON.getCode())) {
			float div = (float) joursRetard / 365;
			return 1 * ((0.1f * facture.getPrixTotalTTC() * div) + 40);

		}
		return 0f;
	}

	/**
	 * 
	 * @param prestation
	 * @param moisFacture
	 * @return
	 */
	public static String calculerDateEcheance(Prestation prestation, String moisFacture) {

		if (prestation == null || moisFacture == null || moisFacture.equals("")) {
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
		String dateFacture = dateActuelle.getYear() + TIRET + moisId[0] + TIRET + "01";
		LocalDate date = LocalDate.parse(dateFacture);
		LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());
		LocalDate dateEcheance = endOfMonth.plusDays(delai);
		return convertToDateFromLocalDate(dateEcheance);
	}

	/**
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public static String convertToDateFromLocalDate(LocalDate dateToConvert) {
		if (dateToConvert == null) {
			return null;
		}
		final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formaterDate.format(dateToConvert);
	}

	/**
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public static String calculDateFacturation(String moisFacture) {

		if(moisFacture == null || moisFacture.equals("")) {
			return null;
		}
		String[] moisId = new String[1];

		mapMois.forEach((key, value) -> {
			if (value.equalsIgnoreCase(moisFacture)) {
				moisId[0] = key;
			}
		});

		LocalDate dateActuelle = LocalDate.now();
		int mois = Integer.parseInt(moisId[0]);

		LocalDate initial = LocalDate.of(dateActuelle.getYear(), mois, 01);
		LocalDate endOfMonth = initial.withDayOfMonth(initial.lengthOfMonth());
		String dateFacture = endOfMonth.getMonth().maxLength() + SLATSH + moisId[0] + SLATSH + dateActuelle.getYear();
		return dateFacture;
	}

	/**
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public static String convertFromDomainToEntityDate(String dateToConvert) {
		if (dateToConvert == null || dateToConvert.isEmpty()) {
			return null;
		}
		String tab[] = dateToConvert.split("-");
		return tab[2] + "/" + tab[1] + "/" + tab[0];
	}

	/**
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public static String convertFromEntityToDomainDate(String dateToConvert) {
		if (dateToConvert == null || dateToConvert.isEmpty()) {
			return null;
		}

		String tab[] = dateToConvert.split("/");
		return tab[2] + "-" + tab[1] + "-" + tab[0];
	}

	/**
	 * 
	 * @param inputFile
	 * @return
	 */
	public static byte[] encodeToBase64(byte[] inputFile) {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encode(inputFile);

	}

	/**
	 * 
	 * @return
	 */
	public static String determinerMoisFacture() {
		Month mois = LocalDate.now().getMonth();
		mois = mois.minus(1);
		String formatMois = mois.getDisplayName(TextStyle.FULL, Locale.FRENCH);
		formatMois = formatMois.substring(0, 1).toUpperCase() + formatMois.substring(1, formatMois.length());
		return formatMois;
	}

	/**
	 * 
	 * @param rsClient
	 * @param listeFactures
	 * @param moisId
	 * @return
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
			if (rsClient != null && client != null && client.toLowerCase().equals(rsClient)) {
				numeroFacture = facture.getNumeroFacture();
				String endNumero[] = numeroFacture.split("-");
				Integer numero = Integer.parseInt(endNumero[1]);
				numeros.add(numero);
			}
		}
		int max = Collections.max(numeros);
		return Utils.buildNumeroFacture(String.valueOf(max + 1), moisId);
	}

	/**
	 * 
	 * @param endNumero
	 * @param moisFacture
	 * @return
	 */
	public static String buildNumeroFacture(String endNumero, Long moisFacture) {

		LocalDate dateActuelle = LocalDate.now();
		String dateFacture = dateActuelle.getYear() + TIRET + moisFacture + TIRET + "01";
		LocalDate date = LocalDate.parse(dateFacture);
		LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());
		String dateConvert = endOfMonth.toString().replace("-", "");
		return dateConvert + "-" + endNumero;
	}

	/**
	 * 
	 * @param pathComplet
	 * @param rootPath
	 * @return
	 */
	public static String buildPath(String pathComplet, String rootPath) {
		String path = null;
		if (pathComplet != null && rootPath != null) {
			int lg = rootPath.length();
			path = pathComplet.substring(lg, pathComplet.length());
		}
		return path;
	}

	/**
	 * 
	 * @param pathComplet
	 * @param fileName
	 * @return
	 */
	public static String buildPathSuivi(String pathComplet, String fileName) {
		String path = null;
		int anneeCourante = LocalDate.now().getYear();
		path = pathComplet + File.separator + anneeCourante + File.separator + fileName;

		return path;
	}

	/**
	 * 
	 * @param pathComplet
	 * @return
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
	 * @param notionJourFerie
	 *            prendre en compte les jours fériés
	 * @param priseCompteLundi
	 *            prendre en compte les Lundi
	 * @param priseCompteMardi
	 *            prendre en compte les Mardi
	 * @param priseCompteMercredi
	 *            prendre en compte les Mercredi
	 * @param priseCompteJeudi
	 *            prendre en compte les Jeudi
	 * @param priseCompteVendredi
	 *            prendre en compte Vendredi
	 * @param priseCompteSamedi
	 *            prendre en compte les Samedi
	 * @param priseCompteDimanche
	 *            prendre en compte les Dimanche
	 * @return
	 */
	public static int nbJours(Date d1, Date d2, boolean notionJourFerie, boolean priseCompteLundi,
			boolean priseCompteMardi, boolean priseCompteMercredi, boolean priseCompteJeudi,
			boolean priseCompteVendredi, boolean priseCompteSamedi, boolean priseCompteDimanche) {

		if (d2.compareTo(d1) <= 0)
			return 0;

		// Tableau des jours a prendre en compte
		Boolean[] joursPrisEncompte = new Boolean[] { priseCompteDimanche, priseCompteLundi, priseCompteMardi,
				priseCompteMercredi, priseCompteJeudi, priseCompteVendredi, priseCompteSamedi };

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

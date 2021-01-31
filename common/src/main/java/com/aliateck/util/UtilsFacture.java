package com.aliateck.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.util.ResourceUtils;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public class UtilsFacture {

	private static final String REGEX = "_";

	private UtilsFacture() {
	}

	/*
	 *
	 */

	public static Facture updateFacture(Facture oFacture, Facture factureRequest) {

		if (factureRequest != null && factureRequest.getDateEncaissement() != null
				&& !factureRequest.getDateEncaissement().isEmpty()) {
			String dateEncaissement = UtilsFacture.convertDomainToEntityDate(factureRequest.getDateEncaissement());
			oFacture.setDateEncaissement(dateEncaissement);
			oFacture.setFactureStatus(FactureStatus.OUI.getCode());
		}
		return oFacture;

	}

	public static LocalDate convertStringToDate(String dateToConvert) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(dateToConvert, formatter);
	}

	public static long calculerNbJourRetard(Facture facture) {
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
	public static float calculerFraisRetard(Facture facture) {
		if (facture.getFactureStatus().equalsIgnoreCase(FactureStatus.NON.getCode())) {
			float div = (float) facture.getNbJourRetard() / 365;
			return (2.52f / 100) * facture.getPrixTotalHT() * div;

		}
		return 0f;
	}

	/*
	 *
	 */
	public static String calculerDateEcheance(Prestation prestation) {
		long delai = prestation.getDelaiPaiement();
		LocalDate dateEcheance = LocalDate.now().plusDays(delai);
		return convertToDateFromLocalDate(dateEcheance);
	}

	/*
	 *
	 */
	public static String convertToDateFromLocalDate(LocalDate dateToConvert) {
		final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formaterDate.format(dateToConvert);
	}

	/*
	 *
	 */
	public static String convertDomainToEntityDate(String dateToConvert) {
		String tab[] = dateToConvert.split("-");
		return tab[2] + "/" + tab[1] + "/" + tab[0];
	}

	/*
	 *
	 */
	public static String convertFromEntityToDomainDate(String dateToConvert) {
		String tab[] = dateToConvert.split("/");
		return tab[2] + "-" + tab[1] + "-" + tab[0];
	}

	/*
	 *
	 */
	public static String determinerMoisFacture() {
		Month mois = LocalDate.now().getMonth();
		mois = mois.minus(1);
		String formatMois = mois.getDisplayName(TextStyle.FULL, Locale.FRENCH);
		formatMois = formatMois.substring(0, 1).toUpperCase() + formatMois.substring(1, formatMois.length());
		return formatMois;
	}

	/*
	*
	*/
	public static String updateNumeroFacture(String rsClient, List<Facture> listeFactures) {

		Set<Integer> numeros = new HashSet<>();
		numeros.add(1000);
		String numeroFacture = null;
		if (listeFactures == null || listeFactures.isEmpty()) {
			return UtilsFacture.buildNumeroFacture("1000");
		}
		for (Facture facture : listeFactures) {
			String filePath = facture.getFilePath();
			String replace = filePath.replaceAll("\\\\", REGEX);
			replace = replace.substring(1, replace.length());
			String[] raisonSociale = replace.split(REGEX);
			if (rsClient != null && raisonSociale != null && rsClient.equalsIgnoreCase(raisonSociale[2])) {
				numeroFacture = facture.getNumeroFacture();
				String endNumero[] = numeroFacture.split("-");
				Integer numero = Integer.parseInt(endNumero[1]);
				numeros.add(numero);
			}
		}
		int max = Collections.max(numeros);
		return UtilsFacture.buildNumeroFacture(String.valueOf(max + 1));
	}

	/*
	 *
	 */
	public static String buildNumeroFacture(String endNumero) {
		final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate dateJour = LocalDate.now();
		return formaterDate.format(dateJour) + "-" + endNumero;
	}

	/*
	 *
	 */
	public static Map<String, File> loadJasperFile() throws FileNotFoundException {
		Map<String, File> map = new HashMap<>();
		map.put("Default", ResourceUtils.getFile("classpath:data/defaultTemplate.jrxml"));
		map.put("Custom", ResourceUtils.getFile("classpath:data/customTemplate.jrxml"));
		return map;
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

}

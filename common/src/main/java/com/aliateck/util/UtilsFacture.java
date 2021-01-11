package com.aliateck.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.util.ResourceUtils;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

public class UtilsFacture {

	private UtilsFacture() {
	}

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
		mois = mois.minus(1);
		String formatMois = mois.getDisplayName(TextStyle.FULL, Locale.FRENCH);
		formatMois = formatMois.substring(0, 1).toUpperCase() + formatMois.substring(1, formatMois.length());
		return formatMois;
	}

	/*
	*
	*/
	public static String updateNumeroFacture(List<Facture> listeFactures) {

		Set<Integer> numeros = new HashSet<>();
		String numeroFacture = null;
		if(listeFactures == null || listeFactures.isEmpty()) {
			return UtilsFacture.buildNumeroFacture("1000");	
		}
		for (Facture facture : listeFactures) {
			numeroFacture = facture.getNumeroFacture();
			String endNumero[] = numeroFacture.split("-");
			Integer numero = Integer.parseInt(endNumero[1]);
			numeros.add(numero);
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

	public static File loadJasperFile() throws FileNotFoundException {
		return ResourceUtils.getFile("classpath:data/factureDesign.jrxml");
	}
	
	public static String buildPath(String pathComplet, String rootPath)  {
		String path = null;
		if(pathComplet != null && rootPath != null) {
			int lg = rootPath.length();
			path = pathComplet.substring(lg, pathComplet.length());
		}
		return path;
	}
}

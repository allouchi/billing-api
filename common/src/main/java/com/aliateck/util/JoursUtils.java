package com.aliateck.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class JoursUtils {

	private JoursUtils() {
	}

	/**
	 * nombre de jours entre 2 dates
	 * 
	 * @param d1
	 * @param d2
	 * @param notionJourFerie     prendre en compte les jours fériés
	 * @param priseCompteLundi    prendre en compte les Lundi
	 * @param priseCompteMardi    prendre en compte les Mardi
	 * @param priseCompteMercredi prendre en compte les Mercredi
	 * @param priseCompteJeudi    prendre en compte les Jeudi
	 * @param priseCompteVendredi prendre en compte Vendredi
	 * @param priseCompteSamedi   prendre en compte les Samedi
	 * @param priseCompteDimanche prendre en compte les Dimanche
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
	public static List<Date> getJourFeries(int annee) {
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

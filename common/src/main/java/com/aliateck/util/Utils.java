package com.aliateck.util;

import java.util.HashMap;
import java.util.Map;

import com.aliateck.fact.domaine.business.object.Consultant;

public class Utils {

	private static Map<String, String> mapMois = new HashMap<>();
	
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
	

	private Utils() {
	}

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

}

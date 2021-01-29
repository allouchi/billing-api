package com.aliateck.util;

import com.aliateck.fact.domaine.business.object.Consultant;

public class Utils {

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

}

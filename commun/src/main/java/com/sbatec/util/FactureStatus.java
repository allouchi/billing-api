package com.sbatec.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FactureStatus {
	
	OUI("OK", "Acquitté"), NON("KO", "N. Acquitté");

	private String code;
	private String description;
}

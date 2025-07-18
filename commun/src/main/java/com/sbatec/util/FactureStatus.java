package com.sbatec.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FactureStatus {

    OUI("OK", "Acquittée"), NON("KO", "Non Acquittée");

    private String code;
    private String description;
}

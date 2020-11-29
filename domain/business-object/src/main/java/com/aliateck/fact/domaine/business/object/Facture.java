package com.aliateck.fact.domaine.business.object;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Facture {
	
	Long id;
	
	String numeroFacture;
	
	Date dateFacturation;
	
	Date dateEcheance;
	
    Date dateEncaissement;
    
    float montantHT;
    
    float montantTTC;
    
    long nbJourRetard;
    
    long delaiFacturation;
    
    float fraisRetard;
    
    String statusFacture;
}

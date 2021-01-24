package com.aliateck.fact.domaine.business.object;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Prestation {
	Long id;
	Consultant consultant;
	Client client;
	List<Facture> facture;
	int tarifHT;
	long delaiPaiement;
	String numeroCommande;
	Float quantite;
	String clientPrestation;
	String designation;
}

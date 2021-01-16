package com.aliateck.fact.domaine.business.object;

import java.util.List;

import com.aliateck.fact.domaine.business.object.common.Domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company extends Domain {
	Long id;
	String socialReason;
	String status;
	String siret;
	String rcsName;
	String numeroTva;
	String codeApe;
	String codeIban;
	String codeBic;	
	Adresse companyAdresse;
	List<Client> clients;
	List<Consultant> consultants;
	List<Prestation> prestations;
}

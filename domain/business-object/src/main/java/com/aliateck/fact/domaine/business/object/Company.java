package com.aliateck.fact.domaine.business.object;

import com.aliateck.fact.domaine.business.object.common.Domain;

import java.io.Serializable;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company extends Domain implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Long id;
  String socialReason;
  String status;
  String siret;
  String rcsName;
  String numeroTva;
  String codeApe;
  String numeroIban;
  String numeroBic;
  Adresse companyAdresse;
  List<Client> clients;
  List<Consultant> consultants;
  List<Prestation> prestations;
}

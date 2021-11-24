package com.aliateck.fact.infrastructure.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.aliateck.fact.infrastructure.models.common.CommonEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "T_Facture")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureEntity extends CommonEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;

	@Column(name = "numero_facture")
	String numeroFacture;

	@Column(name = "date_facturation", nullable = false)
	String dateFacturation;

	@Column(name = "date_echeance", nullable = false)
	String dateEcheance;

	@Column(name = "date_encaissement")
	String dateEncaissement;

	@Column(name = "tarifht", nullable = false)
	Float tarifHT;

	@Column(name = "montant_tva", nullable = false)
	Float montantTVA;

	@Column(name = "prix_totalht", scale = 2, nullable = false)
	Float prixTotalHT;

	@Column(name = "prix_totalttc", scale = 2, nullable = false)
	Float prixTotalTTC;

	@Column(name = "nbJour_retard")
	Long nbJourRetard;

	@Column(name = "quantite", nullable = false)
	Float quantite;

	@Column(name = "delai_paiement", nullable = false)
	Long delaiPaiement;

	@Column(name = "mois_facture")
	String moisFacture;

	@Column(name = "frais_retard")
	Float fraisRetard;

	@Column(name = "facture_status")
	String factureStatus;

	@Column(name = "status_desc")
	String statusDesc;

	@Column(name = "file_path")
	String filePath;

	@Column(name = "file_name")
	String fileName;

	@Lob
	@Column(name = "file_content", length = 10000000, columnDefinition = "longblob")
	byte[] fileContent;

	@Column(name = "numero_commande", nullable = false)
	String numeroCommande;

	@Column(name = "client_prestation", nullable = false)
	String clientPrestation;

}

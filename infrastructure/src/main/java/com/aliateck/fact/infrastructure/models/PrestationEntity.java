package com.aliateck.fact.infrastructure.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.aliateck.fact.infrastructure.models.common.CommonEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "T_Prestation")
@EqualsAndHashCode(callSuper=false)
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationEntity extends CommonEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;

	@Column(name = "delai_paiement", nullable = false)
	Long delaiPaiement;

	@Column(name = "tarifht", nullable = false)
	Float tarifHT;

	@Column(name = "numero_commande", nullable = false)
	String numeroCommande;

	@Column(name = "quantite", nullable = false)
	Float quantite;

	@Column(name = "designation", nullable = false)
	String designation;

	@Column(name = "client_prestation", nullable = false)
	String clientPrestation;

	@Column(name = "date_debut", nullable = true)
	String dateDebut;

	@Column(name = "date_fin", nullable = true)
	String dateFin;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "client_id")
	private ClientEntity client;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "consultant_id")
	private ConsultantEntity consultant;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "facture_id")
	private List<FactureEntity> facture;

}

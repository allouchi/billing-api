package com.sbatec.fact.infrastructure.models;

import com.sbatec.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@Entity(name = "T_Facture")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class FactureEntity extends CommonEntity {

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

    @Column(name = "montant_net_tva", nullable = false)
    Float montantNetTVA;

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

    @Column(name = "exercice", nullable = false)
    String exercice;

    @Lob
    @Column(name = "file_content", length = 10000000, columnDefinition = "longblob")
    byte[] fileContent;

    @Column(name = "numero_commande", nullable = false)
    String numeroCommande;

    @Column(name = "client_prestation", nullable = false)
    String clientPrestation;

}

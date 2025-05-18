package com.aliateck.fact.infrastructure.models;

import com.aliateck.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "T_Prestation")
@EqualsAndHashCode(callSuper = false)
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationEntity extends CommonEntity {

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

    @Column(name = "client_prestation")
    String clientPrestation;

    @Column(name = "date_debut", nullable = true)
    String dateDebut;

    @Column(name = "date_fin", nullable = true)
    String dateFin;

    @Column(name = "siret", nullable = true)
    String siret;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "consultant_id")
    private ConsultantEntity consultant;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "prestation_id")
    private List<FactureEntity> factures;

}

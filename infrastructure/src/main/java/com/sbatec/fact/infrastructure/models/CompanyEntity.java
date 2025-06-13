package com.sbatec.fact.infrastructure.models;

import com.sbatec.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity(name = "T_Company")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@ToString
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyEntity extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "social_reason", nullable = false)
    String socialReason;

    @Column(name = "status", nullable = false)
    String status;

    @Column(name = "siret", nullable = false, unique = true, length = 50)
    String siret;

    @Column(name = "rcsname")
    String rcsName;

    @Column(name = "numero_tva")
    String numeroTva;

    @Column(name = "code_ape")
    String codeApe;

    @Column(name = "numero_iban")
    String numeroIban;

    @Column(name = "numero_bic")
    String numeroBic;
    @Column(name = "checked")
    Boolean checked;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "adresse_id")
    private AdresseEntity companyAdresse;

    //@JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "company_id")
    private List<ConsultantEntity> consultants;

    //@JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "company_id")
    private List<ClientEntity> clients;

    //@JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "company_id")
    private List<PrestationEntity> prestations;

}

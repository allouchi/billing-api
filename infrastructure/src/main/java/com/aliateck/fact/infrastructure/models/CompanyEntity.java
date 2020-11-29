package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Entity(name = "T_Company")
public class CompanyEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "socialreason")
  String socialReason;

  @Column(name = "status")
  String status;

  @Column(name = "siret", nullable = false)
  String siret;

  @Column(name = "rcsname")
  String rcsName;

  @Column(name = "tvaname")
  String tvaName;

  @Column(name = "ape")
  String ape;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "adresse_id", referencedColumnName = "id")
  private CompanyAdresseEntity companyAdresse;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "company_id")
  private List<UserEntity> users;

  @OneToMany(
    targetEntity = ClientEntity.class,
    mappedBy = "companyClient",
    cascade = CascadeType.ALL
  )
  private List<ClientEntity> clients;
  //
  //  @OneToMany(mappedBy = "companyConsultant", cascade = CascadeType.ALL)
  //  private List<ConsultantEntity> consultants;
}

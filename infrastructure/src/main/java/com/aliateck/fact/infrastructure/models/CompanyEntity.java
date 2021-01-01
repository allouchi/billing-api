package com.aliateck.fact.infrastructure.models;

import com.aliateck.fact.infrastructure.models.common.CommonEntity;
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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "socialreason", nullable = false)
  String socialReason;

  @Column(name = "status", nullable = false)
  String status;

  @Column(name = "siret", nullable = false, unique = true, length = 14)
  String siret;

  @Column(name = "rcsname")
  String rcsName;

  @Column(name = "numeroTva")
  String numeroTva;

  @Column(name = "ape")
  String ape;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "company")
  private AdresseEntity companyAdresse;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "company")
  private List<ConsultantEntity> consultants;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "company")
  private List<ClientEntity> clients;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "company")
  private List<PrestationEntity> prestations;
}

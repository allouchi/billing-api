package com.aliateck.fact.infrastructure.models;

import com.aliateck.fact.infrastructure.models.common.CommonEntity;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity(name = "T_Company")
@AllArgsConstructor
@NoArgsConstructor
@Data
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

  @Column(name = "socialreason")
  String socialReason;

  @Column(name = "status")
  String status;

  @Column(name = "siret", nullable = false, unique = true, length = 14)
  String siret;

  @Column(name = "rcsname")
  String rcsName;

  @Column(name = "numeroTva")
  String numeroTva;

  @Column(name = "ape")
  String ape;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "company")
  private AdresseEntity companyAdresse;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "company")
  private List<ClientEntity> clients;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "company")
  private List<ConsultantEntity> consultants;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "company")
  private List<PrestationEntity> prestations;
}

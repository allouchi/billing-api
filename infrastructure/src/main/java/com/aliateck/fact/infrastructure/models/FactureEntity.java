package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "T_Facture")
public class FactureEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "facture_id", nullable = false)
  Long id;

  @Column(name = "numeroFacture")
  String numeroFacture;

  @Column(name = "dateFacturation")
  Date dateFacturation;

  @Column(name = "dateEcheance")
  Date dateEcheance;

  @Column(name = "dateEncaissement")
  Date dateEncaissement;

  @Column(name = "montantHT")
  float montantHT;

  @Column(name = "montantTTC")
  float montantTTC;

  @Column(name = "nombreRetard")
  long nbJourRetard;

  @Column(name = "delaiFacturation")
  long delaiFacturation;

  @Column(name = "fraisRetard")
  float fraisRetard;

  @Column(name = "status")
  String status;
}

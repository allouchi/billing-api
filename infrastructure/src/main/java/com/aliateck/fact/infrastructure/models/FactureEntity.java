package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity(name = "T_Facture")
@AllArgsConstructor
@Data
@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "prestation")
  private PrestationEntity prestation;

  @Column(name = "numeroFacture", nullable = false, unique = true)
  String numeroFacture;

  @Column(name = "dateFacturation")
  String dateFacturation;

  @Column(name = "dateEcheance")
  String dateEcheance;

  @Column(name = "dateEncaissement")
  String dateEncaissement;

  @Column(name = "tva")
  float tva;

  @Column(name = "prixTotalHT", scale = 2)
  float prixTotalHT;

  @Column(name = "prixTotalTTC", scale = 2)
  float prixTotalTTC;

  @Column(name = "nbJourRetard")
  long nbJourRetard;

  @Column(name = "fraisRetard")
  float fraisRetard;

  @Column(name = "factureStatus")
  String factureStatus;
}

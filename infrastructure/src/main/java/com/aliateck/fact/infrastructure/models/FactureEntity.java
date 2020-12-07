package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "numeroFacture")
  String numeroFacture;

  @Column(name = "dateFacturation")
  String dateFacturation;

  @Column(name = "dateEcheance")
  String dateEcheance;

  @Column(name = "dateEncaissement")
  String dateEncaissement;

  @Column(name = "tarifHT")
  float tarifHT;

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

  @Column(name = "status")
  String factureStatus;

  @OneToOne(mappedBy = "facture")
  PrestationEntity prestation;
}

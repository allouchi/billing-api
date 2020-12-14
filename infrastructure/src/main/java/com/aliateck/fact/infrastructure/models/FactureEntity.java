package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "T_Facture")
@AllArgsConstructor
@NoArgsConstructor
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

  @Column(name = "numeroFacture", nullable = false, unique = true)
  String numeroFacture;

  @Column(name = "dateFacturation")
  String dateFacturation;

  @Column(name = "dateEcheance")
  String dateEcheance;

  @Column(name = "dateEncaissement")
  String dateEncaissement;

  @Column(name = "tva")
  Float tva;

  @Column(name = "prixTotalHT", scale = 2)
  Float prixTotalHT;

  @Column(name = "prixTotalTTC", scale = 2)
  Float prixTotalTTC;

  @Column(name = "nbJourRetard")
  Long nbJourRetard;

  @Column(name = "delaiPaiement")
  Long delaiPaiement;

  @Column(name = "fraisRetard")
  Float fraisRetard;

  @Column(name = "nbJoursEffectues")
  Float nbJoursEffectues;

  @Column(name = "factureStatus")
  String factureStatus;
}

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
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "T_Prestation")
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "numeroCommande")
  String numeroCommande;

  @Column(name = "delaiPaiement")
  Long delaiPaiement;

  @Column(name = "tarifHT")
  Integer tarifHT;

  @Column(name = "nbJoursEffectues")
  Float nbJoursEffectues;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "client")
  private ClientEntity client;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "consultant")
  private ConsultantEntity consultant;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "facture")
  private FactureEntity facture;
}

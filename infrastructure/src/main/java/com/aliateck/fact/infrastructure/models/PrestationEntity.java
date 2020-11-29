package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "T_Prestation")
public class PrestationEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "prestation_id", nullable = false)
  Long id;

  @Column(name = "nbJoursEffectue")
  int nbJoursEffectue;

  @Column(name = "tarif")
  int tarif;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "facture_id", nullable = false)
  private FactureEntity facture;
}

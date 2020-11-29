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
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Entity(name = "T_AdresseClient")
public class ClientAdresseEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "numero")
  String numero;

  @Column(name = "voie")
  String voie;

  @Column(name = "codePostal")
  String codePostal;

  @Column(name = "commune")
  String commune;

  @Column(name = "pays")
  String pays;
}

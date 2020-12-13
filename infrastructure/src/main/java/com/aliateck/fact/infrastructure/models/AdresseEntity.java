package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity(name = "T_Adresse")
@AllArgsConstructor
@Data
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdresseEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "numero")
  String numero;

  @Column(name = "complementAdresse")
  String complementAdresse;

  @Column(name = "voie")
  String voie;

  @Column(name = "codePostal")
  String codePostal;

  @Column(name = "commune")
  String commune;

  @Column(name = "pays")
  String pays;
}

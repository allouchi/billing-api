package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "T_Adresse")
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
@Builder
@ToString
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdresseEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "numero", nullable = false)
  String numero;  

  @Column(name = "rue", nullable = false)
  String rue;

  @Column(name = "code_postal", nullable = false)
  String codePostal;

  @Column(name = "localite", nullable = false)
  String localite;

  @Column(name = "pays", nullable = false)
  String pays;  
 
}

package com.aliateck.fact.infrastructure.models;

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


@Entity(name = "T_Tva")
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
@Builder
@ToString
public class TvaEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;
  
  @Column(name = "exercice", nullable = false)
  String exercice;  
  
  @Column(name = "datePayment", nullable = false)
  String datePayment;
  
  @Column(name = "montantPayment", nullable = false)
  Float montantPayment;

}

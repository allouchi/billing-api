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

@Entity(name = "T_Consultant")
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
@Builder
@ToString
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultantEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "firstName", nullable = false)
  String firstName;

  @Column(name = "lastName", nullable = false)
  String lastName;

  @Column(name = "mail", unique = true, nullable = false)
  String mail;
}

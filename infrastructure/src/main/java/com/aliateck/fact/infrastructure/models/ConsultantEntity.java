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
import lombok.ToString;

@Entity(name = "T_Consultant")
@AllArgsConstructor
@Data
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

  @Column(name = "firstName")
  String firstName;

  @Column(name = "lastName")
  String lastName;

  @Column(name = "mail")
  String mail;
}

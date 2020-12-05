package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "T_User")
public class UserEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "firstname")
  String firstName;

  @Column(name = "lastname")
  String lastName;

  @Column(name = "mail", unique = true, nullable = false, length = 500)
  String mail;

  @Column(name = "password")
  String password;

  @Column(name = "role")
  String role;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "company_id")
  CompanyEntity company;
}

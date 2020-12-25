package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "T_User")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "firstname", nullable = false)
  String firstName;

  @Column(name = "lastname", nullable = false)
  String lastName;

  @Column(name = "mail", unique = true, nullable = false, length = 500)
  String mail;

  @Column(name = "password", nullable = false)
  String password;

  @Column(name = "role", nullable = false)
  String role;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "company")
  private CompanyEntity company;
}

package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
@Entity(name = "T_Consultant")
public class ConsultantEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "firstName")
  String firstName;

  @Column(name = "lastName")
  String lastName;

  @Column(name = "mail")
  String mail;
  
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private PrestationEntity prestation;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
      name = "T_Consltant",
      joinColumns = @JoinColumn(name = "consultant_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id")
    )
  private List<ClientEntity> clients;
}

package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "T_Client")
public class ClientEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "socialReason", nullable = false)
  String socialReason;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "company_id")
  CompanyEntity companyClient;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "adresse_id")
  ClientAdresseEntity adresse;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "T_Prestation",
    joinColumns = @JoinColumn(name = "client_id"),
    inverseJoinColumns = @JoinColumn(name = "consultant_id")
  )
  List<ConsultantEntity> consultants;
}

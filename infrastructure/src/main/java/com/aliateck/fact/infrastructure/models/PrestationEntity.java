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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "T_Prestation")
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationEntity implements Serializable {
  /**
   *
   */private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "delaiPaiement", nullable = false)
  Long delaiPaiement;

  @Column(name = "tarifHT", nullable = false)
  Integer tarifHT;
  
  @Column(name = "numeroCommande", nullable= false)
  String numeroCommande;

  @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "client")
  private ClientEntity client;

  @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "consultant")
  private ConsultantEntity consultant;

  @OneToMany(fetch = FetchType.LAZY,  cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} )
  @JoinColumn(name = "facture")
  private List<FactureEntity> factures;
  
}

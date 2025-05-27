package com.sbatec.fact.domaine.business.object;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Prestation {
    Long id;
    Consultant consultant;
    Client client;
    List<Facture> facture;
    Float tarifHT;
    long delaiPaiement;
    String numeroCommande;
    Float quantite;
    String clientPrestation;
    String designation;
    String dateDebut;
    String dateFin;
    String siret;
    
}

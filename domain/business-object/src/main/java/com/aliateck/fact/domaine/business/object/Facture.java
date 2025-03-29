package com.aliateck.fact.domaine.business.object;

import lombok.*;
import lombok.experimental.FieldDefaults;

// @Data
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// @JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Facture {
    Long id;
    String numeroFacture;
    String dateFacturation;
    String dateEcheance;
    String dateEncaissement;
    float montantTVA;
    float montantNetTVA;
    float prixTotalHT;
    float prixTotalTTC;
    long nbJourRetard;
    float fraisRetard;
    Float tarifHT;
    long delaiPaiement;
    String factureStatus;
    String statusDesc;
    float quantite;
    String numeroCommande;
    String clientPrestation;
    String filePath;
    String fileName;
    byte[] fileContent;
    String moisFacture;
    String exercice;
}

package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureMapper {

  public FactureEntity fromDomainToEntity(Facture domain) {
    return FactureEntity
      .builder()
      .id(domain.getId())
      .dateEcheance(domain.getDateEcheance())
      .dateEncaissement(domain.getDateEncaissement())
      .dateFacturation(domain.getDateFacturation())
      .fraisRetard(domain.getFraisRetard())
      .nbJourRetard(domain.getNbJourRetard())
      .delaiPaiement(domain.getDelaiPaiement())
      .factureStatus(domain.getFactureStatus())
      .numeroFacture(domain.getNumeroFacture())
      .prixTotalHT(domain.getPrixTotalHT())
      .prixTotalTTC(domain.getPrixTotalTTC())
      .montantTVA(domain.getMontantTVA())
      .moisFacture(domain.getMoisFacture())
      .quantite(domain.getQuantite())
      .designation(domain.getDesignation())
      .numeroCommande(domain.getNumeroCommande())
      .clientPrestation(domain.getClientPrestation())
      .build();
  }

  public Facture fromEntityToDomain(FactureEntity entity) {
    if (entity == null) {
      return null;
    }
    return Facture
      .builder()
      .id(entity.getId())
      .dateEcheance(entity.getDateEcheance())
      .dateEncaissement(entity.getDateEncaissement())
      .dateFacturation(entity.getDateFacturation())
      .fraisRetard(entity.getFraisRetard())
      .prixTotalHT(entity.getPrixTotalHT())
      .prixTotalTTC(entity.getPrixTotalTTC())
      .montantTVA(entity.getMontantTVA())
      .nbJourRetard(entity.getNbJourRetard())
      .delaiPaiement(entity.getDelaiPaiement())
      .factureStatus(entity.getFactureStatus())
      .numeroFacture(entity.getNumeroFacture())
      .moisFacture(entity.getMoisFacture())
      .quantite(entity.getQuantite())
      .designation(entity.getDesignation()) 
      .numeroCommande(entity.getNumeroCommande())
      .clientPrestation(entity.getClientPrestation())
      .build();
  }

  public List<Facture> fromEntityToDomain(List<FactureEntity> entities) {
    return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
  }

  public List<FactureEntity> fromDomainToEntity(List<Facture> domains) {
    return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
  }
}

package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
      .factureStatus(domain.getFactureStatus())
      .numeroFacture(domain.getNumeroFacture())
      .tarifHT(domain.getTarifHT())
      .prixTotalHT(domain.getPrixTotalHT())
      .prixTotalTTC(domain.getPrixTotalTTC())
      .tva(domain.getTva())
      .build();
  }

  public Facture fromEntityToDomain(FactureEntity entity) {
    return Facture
      .builder()
      .id(entity.getId())
      .dateEcheance(entity.getDateEcheance())
      .dateEncaissement(entity.getDateEncaissement())
      .dateFacturation(entity.getDateFacturation())
      .fraisRetard(entity.getFraisRetard())
      .tarifHT(entity.getTarifHT())
      .prixTotalHT(entity.getPrixTotalHT())
      .prixTotalTTC(entity.getPrixTotalTTC())
      .tva(entity.getTva())
      .nbJourRetard(entity.getNbJourRetard())
      .factureStatus(entity.getFactureStatus())
      .numeroFacture(entity.getNumeroFacture())
      .build();
  }

  public List<Facture> fromEntityToDomainList(Collection<FactureEntity> entitys) {
    List<Facture> factureList = new ArrayList<>();

    for (FactureEntity entity : entitys) {
      factureList.add(fromEntityToDomain(entity));
    }

    return factureList;
  }
}

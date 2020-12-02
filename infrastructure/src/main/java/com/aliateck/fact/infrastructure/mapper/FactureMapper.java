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
      .montantHT(domain.getMontantHT())
      .montantTTC(domain.getMontantHT())
      .nbJourRetard(domain.getNbJourRetard())
      .status(domain.getStatusFacture())
      .numeroFacture(domain.getNumeroFacture())
      .delaiFacturation(domain.getDelaiFacturation())
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
      .montantHT(entity.getMontantHT())
      .montantTTC(entity.getMontantHT())
      .nbJourRetard(entity.getNbJourRetard())
      .statusFacture(entity.getStatus())
      .numeroFacture(entity.getNumeroFacture())
      .delaiFacturation(entity.getDelaiFacturation())
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

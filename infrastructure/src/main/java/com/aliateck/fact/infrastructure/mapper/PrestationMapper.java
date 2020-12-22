package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationMapper {
  ClientMapper clientMapper;
  ConsultantMapper consultantMapper;

  FactureMapper factureMapper;

  public PrestationEntity fromDomainToEntity(Prestation domain) {
    if (domain == null) {
      return null;
    }
    //Facture facture = CalculerFacture.calculerFacture(domain);
    //domain.setFacture(facture);
    return PrestationEntity
      .builder()
      .id(domain.getId())
      .numeroCommande(domain.getNumeroCommande())
      .client(clientMapper.fromDomainToEntity(domain.getClient()))
      .consultant(consultantMapper.fromDomainToEntity(domain.getConsultant()))
      //.facture(factureMapper.fromDomainToEntity(domain.getFacture()))
      .delaiPaiement(domain.getDelaiPaiement())
      .tarifHT(domain.getTarifHT())
      .nbJoursEffectues(domain.getNbJoursEffectues())
      .build();
  }

  public Prestation fromEntityToDomain(PrestationEntity entity) {
    if (entity == null) {
      return null;
    }
    return Prestation
      .builder()
      .id(entity.getId())
      .numeroCommande(entity.getNumeroCommande())
      .client(clientMapper.fromEntityToDomain(entity.getClient()))
      .consultant(consultantMapper.fromEntityToDomain(entity.getConsultant()))
      //.facture(factureMapper.fromEntityToDomain(entity.getFacture()))
      .delaiPaiement(entity.getDelaiPaiement())
      .tarifHT(entity.getTarifHT())
      .nbJoursEffectues(entity.getNbJoursEffectues())
      .build();
  }

  public List<Prestation> fromEntityToDomain(List<PrestationEntity> entities) {
    return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
  }

  public List<PrestationEntity> fromDomainToEntity(List<Prestation> domains) {
    return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
  }
}

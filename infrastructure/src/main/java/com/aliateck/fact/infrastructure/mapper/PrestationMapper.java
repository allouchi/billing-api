package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        if (domain.getFacture() == null) {
            return PrestationEntity.builder().id(domain.getId())
                    .client(clientMapper.fromDomainToEntity(domain.getClient()))
                    .consultant(consultantMapper.fromDomainToEntity(domain.getConsultant()))
                    .delaiPaiement(domain.getDelaiPaiement())
                    .tarifHT(domain.getTarifHT())
                    .numeroCommande(domain.getNumeroCommande())
                    .quantite(domain.getQuantite())
                    .clientPrestation(domain.getClientPrestation())
                    .designation(domain.getDesignation())
                    .dateDebut(domain.getDateDebut())
                    .dateFin(domain.getDateFin())
                    .siret(domain.getSiret())
                    .build();
        }

        return PrestationEntity.builder()
                .id(domain.getId())
                .client(clientMapper.fromDomainToEntity(domain.getClient()))
                .consultant(consultantMapper.fromDomainToEntity(domain.getConsultant()))
                .facture(factureMapper.fromDomainToEntity(domain.getFacture()))
                .delaiPaiement(domain.getDelaiPaiement())
                .tarifHT(domain.getTarifHT())
                .numeroCommande(domain.getNumeroCommande())
                .quantite(domain.getQuantite())
                .clientPrestation(domain.getClientPrestation())
                .designation(domain.getDesignation())
                .dateDebut(domain.getDateDebut())
                .dateFin(domain.getDateFin())
                .siret(domain.getSiret())
                .build();
    }

    public Prestation fromEntityToDomain(PrestationEntity entity) {
        if (entity == null) {
            return null;
        }

        if (entity.getFacture() == null) {
            return Prestation.builder()
                    .id(entity.getId())
                    .client(clientMapper.fromEntityToDomain(entity.getClient()))
                    .consultant(consultantMapper.fromEntityToDomain(entity.getConsultant()))
                    .delaiPaiement(entity.getDelaiPaiement())
                    .tarifHT(entity.getTarifHT())
                    .numeroCommande(entity.getNumeroCommande())
                    .quantite(entity.getQuantite())
                    .clientPrestation(entity.getClientPrestation())
                    .designation(entity.getDesignation())
                    .dateDebut(entity.getDateDebut())
                    .dateFin(entity.getDateFin())
                    .siret(entity.getSiret())
                    .build();
        }
        return Prestation.builder()
                .id(entity.getId())
                .client(clientMapper.fromEntityToDomain(entity.getClient()))
                .consultant(consultantMapper.fromEntityToDomain(entity.getConsultant()))
                .facture(factureMapper.fromEntityToDomain(entity.getFacture()))
                .delaiPaiement(entity.getDelaiPaiement())
                .tarifHT(entity.getTarifHT())
                .numeroCommande(entity.getNumeroCommande())
                .quantite(entity.getQuantite())
                .clientPrestation(entity.getClientPrestation())
                .designation(entity.getDesignation())
                .dateDebut(entity.getDateDebut())
                .dateFin(entity.getDateFin())
                .siret(entity.getSiret())
                .build();
    }

    public List<Prestation> fromEntityToDomain(List<PrestationEntity> entities) {
        if (entities != null) {
            return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<PrestationEntity> fromDomainToEntity(List<Prestation> domains) {

        if (domains != null) {
            return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}

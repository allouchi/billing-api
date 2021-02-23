package com.aliateck.fact.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.infrastructure.models.FactureEntity;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureMapper {

	public FactureEntity fromDomainToEntity(Facture domain) {
		if (domain == null) {
			return null;
		}

		return FactureEntity.builder().id(domain.getId()).dateEcheance(domain.getDateEcheance())
				.dateEncaissement(domain.getDateEncaissement()).dateFacturation(domain.getDateFacturation())
				.fraisRetard(domain.getFraisRetard()).nbJourRetard(domain.getNbJourRetard())
				.delaiPaiement(domain.getDelaiPaiement())
				.factureStatus(domain.getFactureStatus())
				.statusDesc(domain.getStatusDesc())
				.tarifHT(domain.getTarifHT())
				.numeroFacture(domain.getNumeroFacture()).prixTotalHT(domain.getPrixTotalHT())
				.prixTotalTTC(domain.getPrixTotalTTC()).montantTVA(domain.getMontantTVA())
				.quantite(domain.getQuantite())				
				.numeroCommande(domain.getNumeroCommande())
				.moisFacture(domain.getMoisFacture())				
				.clientPrestation(domain.getClientPrestation()).filePath(domain.getFilePath()).build();
	}

	public Facture fromEntityToDomain(FactureEntity entity) {
		if (entity == null) {
			return null;
		}		

		return Facture.builder().
				id(entity.getId())
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
				.statusDesc(entity.getStatusDesc())
				.tarifHT(entity.getTarifHT())
				.numeroFacture(entity.getNumeroFacture())				
				.quantite(entity.getQuantite())				
				.numeroCommande(entity.getNumeroCommande())
				.moisFacture(entity.getMoisFacture())
				.clientPrestation(entity.getClientPrestation())
				.filePath(entity.getFilePath()).build();
	}

	public List<Facture> fromEntityToDomain(List<FactureEntity> entities) {
		return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
	}

	public List<FactureEntity> fromDomainToEntity(List<Facture> domains) {
		return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
	}
}

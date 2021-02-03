package com.aliateck.fact.infrastructure.adapter.batch;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.spi.batch.BatchSpiService;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.repository.batch.BatchJpaRepository;
import com.aliateck.util.UtilsFacture;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BatchSpiAdapter implements BatchSpiService {

	BatchJpaRepository batchJpaRepository;
	FactureMapper factureMepper;
	
	@Override
	public List<Facture> findAllFactures() {
		return factureMepper.fromEntityToDomain(batchJpaRepository.findAll());
	}
	
	@Override
	public void updateFacture(Facture facture) {
		batchJpaRepository.save(factureMepper.fromDomainToEntity(facture));

	}

	@Override
	public Facture calculerFraisRetard(Facture facture) {
		
		LocalDate dateEcheance = UtilsFacture.convertStringToDate(facture.getDateEcheance());
		LocalDate dateJour = LocalDate.now();

		if (dateJour.isAfter(dateEcheance)
				&& (facture.getDateEncaissement() == null || facture.getDateEncaissement().isEmpty())) {
			long nbJoursRetard = UtilsFacture.calculerNbJourRetard(facture);
			float fraisRetard = UtilsFacture.calculerFraisRetard(facture, nbJoursRetard);			
			facture.setFraisRetard(fraisRetard);
			facture.setNbJourRetard(nbJoursRetard);
		}
		return facture;

	}	

}

package com.aliateck.fact.batch.items;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactureWriter implements ItemWriter<Facture> {

	private BatchApiService batchApiService;	
    private FactureJpaRepository factureJpaRepository;	

	public FactureWriter(BatchApiService batchApiService, FactureJpaRepository factureJpaRepository) {		
		this.batchApiService = batchApiService;
		this.factureJpaRepository = factureJpaRepository;
	}



	@Override
	public void write(List<? extends Facture> factures) throws Exception {
		log.info(factures.toString());

		factures.stream().forEach(facture -> {
			log.info("Enregistrement en base de l'objet {} ", facture);
			batchApiService.updateFactures(facture);
		});

	}

}

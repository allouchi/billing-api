package com.aliateck.fact.batch.items;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactureWriter implements ItemWriter<Facture> {

	private BatchApiService batchApiService;

	public FactureWriter(BatchApiService batchApiService) {
		this.batchApiService = batchApiService;
	}

	@Override
	public void write(List<? extends Facture> factures) throws Exception {

		factures.stream().forEach(facture -> {
			log.info("Enregistrement en base de l'objet {} ", facture);
			batchApiService.updateFactures(facture);
		});

	}

}

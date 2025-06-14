package com.sbatec.fact.batch.items;

import org.springframework.batch.item.ItemProcessor;

import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.ports.api.batch.BatchApiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactureProcessor implements ItemProcessor<Facture, Facture> {

	private BatchApiService batchApiService;

	public FactureProcessor(BatchApiService batchApiService) {
		this.batchApiService = batchApiService;
	}

	@Override
	public Facture process(Facture facture) throws Exception {
		log.info("facture processing");		
		return batchApiService.calculerFraisRetard(facture);
	}

}

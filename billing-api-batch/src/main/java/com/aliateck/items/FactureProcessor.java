package com.aliateck.items;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactureProcessor implements ItemProcessor<Facture, Facture>{

	
	@Autowired
	BatchApiService batchApiService;
	
	@Override
	public Facture process(Facture facture) throws Exception {
		log.info("facture processing");
		log.info(facture.toString());
		return batchApiService.calculerFraisRetard(facture);
	
	}

}

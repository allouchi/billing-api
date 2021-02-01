package com.aliateck.items;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactureWriter implements ItemWriter<Facture>{
	
	
	@Autowired
	BatchApiService batchApiService;

	@Override
	public void write(List<? extends Facture> factures) throws Exception {
		log.info(factures.toString());
		
		factures.stream().forEach(facture -> {
            log.info("Enregistrement en base de l'objet {} ", facture);
            batchApiService.updateFactures(facture);
        });
		
		
	}

}

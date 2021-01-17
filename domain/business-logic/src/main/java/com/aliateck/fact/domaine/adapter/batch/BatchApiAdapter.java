package com.aliateck.fact.domaine.adapter.batch;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;
import com.aliateck.fact.domaine.ports.spi.batch.BatchSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BatchApiAdapter implements BatchApiService {
	
	BatchSpiService batchSpiService;

	@Override
	public void calculerFraisRetard(String siret) {
		batchSpiService.calculerFraisRetard(siret);		
	}
	

}

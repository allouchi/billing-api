package com.aliateck.fact.domaine.adapter.batch;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
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
	public Facture calculerFraisRetard(Facture facture) {
		return batchSpiService.calculerFraisRetard(facture);		
	}

	@Override
	public List<Facture> findAllFactures() {		
		return batchSpiService.findAllFactures();
	}

	@Override
	public void updateFactures(Facture factures) {
		batchSpiService.updateFacture(factures);
		
	}	

}

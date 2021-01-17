package com.aliateck.fact.infrastructure.adapter.batch;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.ports.spi.batch.BatchSpiService;
import com.aliateck.fact.infrastructure.repository.batch.BatchJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BatchSpiAdapter implements BatchSpiService{
	
	BatchJpaRepository batchJpaRepository;

	@Override
	public void calculerFraisRetard(String siret) {
		// TODO Auto-generated method stub
		
	}

}

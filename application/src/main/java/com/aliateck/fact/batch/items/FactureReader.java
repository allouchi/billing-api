package com.aliateck.fact.batch.items;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;

public class FactureReader implements ItemReader<Facture> {

	
	private FactureMapper factureMapper;	
    private FactureJpaRepository factureJpaRepository;
    private Iterator<Facture> facturesIterator;    

    public FactureReader(FactureJpaRepository factureJpaRepository, FactureMapper factureMapper) {		
		this.factureJpaRepository = factureJpaRepository;
		this.factureMapper = factureMapper;
	}

	@BeforeStep
    public void before(StepExecution stepExecution) {
    	List<FactureEntity> entities = factureJpaRepository.findByDateEncaissementIsNull();
    	List<Facture> factures = factureMapper.fromEntityToDomain(entities);    	
    	if(factures != null) {
    		facturesIterator = factures.iterator();
    	}
    	
    }

    @Override
    public Facture read() {
        if (facturesIterator != null && facturesIterator.hasNext()) {
            return facturesIterator.next();
        } else {
            return null;
        }
    }

}

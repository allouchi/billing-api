package com.sbatec.fact.batch.items;

import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.infrastructure.mapper.FactureMapper;
import com.sbatec.fact.infrastructure.models.FactureEntity;
import com.sbatec.fact.infrastructure.repository.facture.FactureJpaRepository;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemReader;

import java.util.Iterator;
import java.util.List;

public class FactureReader implements ItemReader<Facture> {


    private FactureMapper factureMapper;
    private FactureJpaRepository factureJpaRepository;
    private Iterator<Facture> facturesIterator;

    public FactureReader(FactureJpaRepository factureJpaRepository, FactureMapper factureMapper) {
        this.factureJpaRepository = factureJpaRepository;
        this.factureMapper = factureMapper;
    }

    //@BeforeStep
    public void before(StepExecution stepExecution) {
        List<FactureEntity> entities = factureJpaRepository.findByDateEncaissementIsNull();
        List<Facture> factures = factureMapper.fromEntityToDomain(entities);
        if (factures != null) {
            facturesIterator = factures.iterator();
        }

    }

    //Override
    public Facture read() {
        if (facturesIterator != null && facturesIterator.hasNext()) {
            return facturesIterator.next();
        } else {
            return null;
        }
    }

}

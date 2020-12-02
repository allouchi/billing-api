package com.aliateck.fact.infrastructure.adapter.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureSpiAdapter implements FactureSpiService {
  FactureJpaRepository factureJpaRepository;
  FactureMapper factureMapper;

  

@Override 
public void addFacture(Facture facture){
	
	factureJpaRepository.save(factureMapper.fromDomainToEntity(facture));
}

@Override 
public void deleteFacture(Facture facture){
	factureJpaRepository.delete(factureMapper.fromDomainToEntity(facture));
}

@Override 
public void updateFacture(Facture facture){
	factureJpaRepository.save(factureMapper.fromDomainToEntity(facture));
}

@Override 
public Facture findById(long id){
	Optional<FactureEntity> entity = factureJpaRepository.findById(id);
	if(entity.isPresent()) {
		return factureMapper.fromEntityToDomain(entity.get());
	}
	return null;
}

  @Override
  public Facture findByNumeroFacture(String numeroFacture) {
    FactureEntity entity = factureJpaRepository.getByNumeroFacture(numeroFacture);
    return factureMapper.fromEntityToDomain(entity);
  }

  @Override
  public List<Facture> findByStatus(String statusFacture) {
    List<FactureEntity> entitys = factureJpaRepository.findByStatus(statusFacture);
    return factureMapper.fromEntityToDomainList(entitys);
  }

  @Override
  public List<Facture> findByDateEcheance(Date dateEcheance) {
    List<FactureEntity> entities = factureJpaRepository.findByDateEcheance(dateEcheance);

    return factureMapper.fromEntityToDomainList(entities);
  }

  @Override
  public List<Facture> findByDateEncaissement(Date dateEncaissement) {
    List<FactureEntity> entitys = factureJpaRepository.findByDateEncaissement(
      dateEncaissement
    );
    return factureMapper.fromEntityToDomainList(entitys);
  }
}

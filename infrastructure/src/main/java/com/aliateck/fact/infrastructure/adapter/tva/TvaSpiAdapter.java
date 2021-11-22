package com.aliateck.fact.infrastructure.adapter.tva;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.ports.spi.tva.TvaSpiService;
import com.aliateck.fact.infrastructure.mapper.TvaMapper;
import com.aliateck.fact.infrastructure.models.TvaEntity;
import com.aliateck.fact.infrastructure.repository.tva.TvaJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TvaSpiAdapter implements TvaSpiService{
  
  TvaMapper tvaMapper;
  TvaJpaRepository tvaJpaRepository;
 

  @Override
  public Tva findByExercice(String exercice) {   
    Optional<TvaEntity>  entity = tvaJpaRepository.findByExercice(exercice);
    return tvaMapper.fromEntityToDomain(entity.get());
  }


  @Override
  public void delteByExercice(String exercice) {
    tvaJpaRepository.deleteByExercice(exercice);
    
  }


  @Override
  public void delteById(Long id) {
	  tvaJpaRepository.deleteById(id);
    
  }


  @Override
  public Tva addTva(Tva Tva) {	 
	 TvaEntity entity =     tvaJpaRepository.saveAndFlush( tvaMapper.fromDomainToEntity(Tva)); 
	 Tva domainTva = tvaMapper.fromEntityToDomain(entity);
	 return domainTva;
	 
  }


  @Override
  public void updateTva(Tva Tva) {
	  
	  TvaEntity entity  = tvaMapper.fromDomainToEntity(Tva);
	  tvaJpaRepository.saveAndFlush(entity);   
    
  } 
  

}

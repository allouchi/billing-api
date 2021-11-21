package com.aliateck.fact.infrastructure.repository.tva;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliateck.fact.infrastructure.models.TvaEntity;


@Repository
public interface TvaJpaRepository extends JpaRepository<TvaEntity, Long> {  
  
  public Optional<TvaEntity> findByExercice(String exercice);  
  
  public void deleteByExercice(String exercice);
  
  public void deleteById(Long id);  
 
  
}

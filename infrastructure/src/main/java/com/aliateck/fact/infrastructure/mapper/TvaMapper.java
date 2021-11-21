package com.aliateck.fact.infrastructure.mapper;

import org.springframework.stereotype.Component;
import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.infrastructure.models.TvaEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TvaMapper {
  
  public TvaEntity fromDomainToEntity(Tva domain) {
    if (domain == null) {
        return null;
    }
    return TvaEntity.builder().id(domain.getId()).exercice(domain.getExercice()).datePayment(domain.getDatePayment()).montantPayment(domain.getMontantPayment()).build();
}  
  
public Tva fromEntityToDomain(TvaEntity entity) {
  
    if (entity == null) {
        return null;
    }
    
    return Tva.builder().id(entity.getId()).exercice(entity.getExercice()).datePayment(entity.getDatePayment()).montantPayment(entity.getMontantPayment()).build();
  }

}

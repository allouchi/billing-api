package com.aliateck.fact.domaine.adapter.tva;

import org.springframework.stereotype.Service;
import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.ports.api.tva.TvaApiService;
import com.aliateck.fact.domaine.ports.spi.tva.TvaSpiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TvaApiAdapter implements TvaApiService{
  
  TvaSpiService tvaSpiService;

  @Override
  public Tva addTva(Tva tva) {   
    return tvaSpiService.addTva(tva);
  }

  @Override
  public void deleteTva(Long id) {
    tvaSpiService.delteById(id);
    
  }

  @Override
  public void updateTva(Tva tva) {
    tvaSpiService.updateTva(tva);
    
  }

  @Override
  public Tva findByExercice(String exercice) {
    // TODO Auto-generated method stub
    return tvaSpiService.findByExercice(exercice);
  }


}

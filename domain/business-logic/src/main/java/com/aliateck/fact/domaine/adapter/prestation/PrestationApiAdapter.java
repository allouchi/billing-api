package com.aliateck.fact.domaine.adapter.prestation;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.api.prestation.PrestationApiService;
import com.aliateck.fact.domaine.ports.spi.prestation.PrestationSpiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationApiAdapter implements PrestationApiService {
  PrestationSpiService prestationSpiService;

  @Override
  public Prestation addPrestation(Prestation prestation, boolean templateChoice,  String siret, Long moisPrestaId) {
    return prestationSpiService.addPrestation(prestation, templateChoice, siret, moisPrestaId);
  } 

  @Override
  public Prestation updatePrestation(Prestation prestation, String siret) {
    return prestationSpiService.updatePrestation(prestation, siret);
  }
   @Override
  public List<Prestation> findAll(String siret) {
    return prestationSpiService.findAll(siret);
  }

  @Override
  public Prestation findById(long id) {
    return prestationSpiService.findById(id);
  }

@Override public void deletePrestation(long id){
	prestationSpiService.deleteById(id);
	
}
}

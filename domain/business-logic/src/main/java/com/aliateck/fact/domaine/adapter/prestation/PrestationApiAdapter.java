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
  public Prestation ajouterPrestation(Prestation prestation) {
    return prestationSpiService.addPrestation(prestation);
  }

  @Override
  public void supprimerPrestation(Prestation prestation) {
    prestationSpiService.deletePrestation(prestation);
  }

  @Override
  public Prestation mettreAJourPrestation(Prestation prestation) {
    return prestationSpiService.updatePrestation(prestation);
  }

  @Override
  public List<Prestation> retournerPrestations() {
    return prestationSpiService.findAll();
  }

  @Override
  public Prestation chercherPrestationParId(long id) {
    return prestationSpiService.findById(id);
  }
}

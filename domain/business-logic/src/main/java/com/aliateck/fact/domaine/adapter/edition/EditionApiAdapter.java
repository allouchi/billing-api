package com.aliateck.fact.domaine.adapter.edition;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.edition.EditionApiService;
import com.aliateck.fact.domaine.ports.spi.edition.EditionSpiService;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionApiAdapter implements EditionApiService {
  EditionSpiService editionSpiService;

  @Override
  public Facture buildFacture(String siret, long idPrestation, Facture facture) {
    return editionSpiService.buildFacture(siret, idPrestation, facture);
  }

  @Override
  public Map<String, Object> editerFacture(
    String siret,
    long idPrestation,
    Facture facture
  ) {
    return editionSpiService.editerFacture(siret, idPrestation, facture);
  }
}

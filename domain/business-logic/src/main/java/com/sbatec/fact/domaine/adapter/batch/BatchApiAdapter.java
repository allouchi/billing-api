package com.sbatec.fact.domaine.adapter.batch;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.ports.api.batch.BatchApiService;
import com.sbatec.fact.domaine.ports.spi.batch.BatchSpiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BatchApiAdapter implements BatchApiService {

  BatchSpiService batchSpiService;

  @Override
  public Facture calculerFraisRetard(Facture facture) {
    Facture fact = batchSpiService.calculerFraisRetard(facture);
    batchSpiService.updateFacture(fact);
    return fact;
  }

  @Override
  public List<Facture> findAllFactures() {
    return batchSpiService.findAllFactures();
  }

  @Override
  public void updateFactures(Facture facture) {
    batchSpiService.updateFacture(facture);

  }

}

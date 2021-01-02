package com.aliateck.fact.domaine.adapter.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureApiAdapter implements FactureApiService {
  FactureSpiService factureSpiService;

  @Override
  public Facture addFacture(String siret, Facture facture, long prestationId) {
    return factureSpiService.addFacture(siret, facture, prestationId);
  }

  @Override
  public void deleteFacture(String siret,Facture facture, long prestationId) {
    factureSpiService.deleteFacture(siret, facture, prestationId);
  }
  
  @Override
  public void deleteById(String siret, long idPrestation, long idFacture) {
	  factureSpiService.deleteById(siret, idPrestation, idFacture );
  }

  @Override
  public Facture updateFacture(String siret, Facture facture, long prestationId) {
    return factureSpiService.updateFacture(siret, facture, prestationId);
  }

  @Override
  public Facture findById(long id) {
    return factureSpiService.findById(id);
  }

  @Override
  public Facture findByNumero(String numeroFacture) {
    return factureSpiService.findByNumeroFacture(numeroFacture);
  }

  @Override
  public List<Facture> findAllByPrestation(String siret, long idPrestation) {
    return factureSpiService.findAllByPrestation(siret, idPrestation);
  }

  @Override
  public List<Facture> findAllBySiret(String siret) {
    return factureSpiService.findAllBySiret(siret);
  }

 
}

package com.aliateck.fact.domaine.adapter.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import java.util.List;
import java.util.Map;

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
  public Map<String, Object> addFacture(String siret, Facture facture, Long prestationId) {
    return factureSpiService.addFacture(siret, facture, prestationId);
  }

  @Override
  public void deleteFacture(String siret,Facture facture, Long prestationId) {
    factureSpiService.deleteFacture(siret, facture, prestationId);
  }
  
  @Override
  public void deleteById(String siret, Long idPrestation, Long idFacture) {
	  factureSpiService.deleteFactureById(siret, idPrestation, idFacture );
  }

  @Override
  public Facture updateFacture(String siret, Facture facture, Long prestationId) {
    return factureSpiService.updateFacture(siret, facture, prestationId);
  }

  @Override
  public Facture findById(Long id) {
    return factureSpiService.findById(id);
  }

  @Override
  public Facture findByNumero(String numeroFacture) {
    return factureSpiService.findByNumeroFacture(numeroFacture);
  }

  @Override
  public List<Facture> findAllByPrestation(String siret, Long idPrestation) {
    return factureSpiService.findAllByPrestation(siret, idPrestation);
  }

  @Override
  public List<Facture> findAllBySiret(String siret) {
    return factureSpiService.findAllBySiret(siret);
  }

 
}

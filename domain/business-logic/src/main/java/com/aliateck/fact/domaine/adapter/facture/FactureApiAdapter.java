package com.aliateck.fact.domaine.adapter.facture;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureApiAdapter implements FactureApiService {
  FactureSpiService factureSpiService;

  @Override
  public Prestation addFacture(String siret, Prestation prestation, Long prestationId, String pathRoot) {
    return factureSpiService.addFacture(siret, prestation, prestationId, pathRoot);
  }

  @Override
  public void deleteFacture(Long factureId) {
    factureSpiService.deleteFacture(factureId);
  }  
  
  @Override
  public Facture updateFacture(Facture facture) {
    return factureSpiService.updateFacture(facture);
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
  public List<Facture> findAllBySiret(String siret) {
    return factureSpiService.findAllBySiret(siret);
  }

 
}

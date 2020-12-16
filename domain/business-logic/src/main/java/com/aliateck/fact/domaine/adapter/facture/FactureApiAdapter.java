package com.aliateck.fact.domaine.adapter.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.common.edition.CalculerFacture;
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
  public Facture ajouterFacture(Facture facture) {
    Facture fact = null;
    Facture base = factureSpiService.addFacture(facture);
    if (base != null) {
      fact = factureSpiService.findById(base.getId());
      fact.setNumeroFacture(CalculerFacture.calulerNumeroFacture(base.getId()));
      factureSpiService.updateFacture(fact);
    }
    return fact;
  }

  @Override
  public void supprimerFacture(Facture facture) {
    factureSpiService.deleteFacture(facture);
  }

  @Override
  public Facture mettreAJourFacture(Facture facture) {
    return factureSpiService.updateFacture(facture);
  }

  @Override
  public Facture chercherFactureParId(long id) {
    return factureSpiService.findById(id);
  }

  @Override
  public Facture chercherFactureParNumero(String numeroFacture) {
    return factureSpiService.findByNumeroFacture(numeroFacture);
  }

  @Override
  public List<Facture> chercherFacturesByCompanyBySiret(String siret) {
    return factureSpiService.findBySiret(siret);
  }
}

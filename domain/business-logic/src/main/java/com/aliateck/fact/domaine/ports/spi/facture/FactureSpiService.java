package com.aliateck.fact.domaine.ports.spi.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import java.util.Date;
import java.util.List;

public interface FactureSpiService {
  Facture getFactureByNumero(String numero);

  List<Facture> getFactureByStatus(String status);

  Facture getFactureByDateEcheance(Date dateEcheance);

  List<Facture> getFactureByDateEncaissement(Date dateEncaissement);

  List<Facture> getFactureByNumero(long numeroClient);
}

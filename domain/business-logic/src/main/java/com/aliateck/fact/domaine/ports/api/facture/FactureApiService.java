package com.aliateck.fact.domaine.ports.api.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import java.util.List;

public interface FactureApiService {
  public Facture addFacture(Facture factureRequest, long prestationId, String numeroCommande);

  public void deleteFacture(Facture facture);

  public Facture updateFacture(Facture facture);

  public Facture findById(long id);

  public Facture findByNumero(String numero);

  public List<Facture> findAllByPrestation(String siret, long idPrestation);
  
  public List<Facture> findAllBySiret(String siret);
}

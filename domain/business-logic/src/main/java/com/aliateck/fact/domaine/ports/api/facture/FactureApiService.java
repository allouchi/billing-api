package com.aliateck.fact.domaine.ports.api.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import java.util.List;

public interface FactureApiService {
  public Facture addFacture(String siret, Facture factureRequest, long prestationId);

  public void deleteFacture(String siret,Facture facture, long prestationId);
  
  public void deleteById(String siret, long idPrestation, long idFacture);

  public Facture updateFacture(String siret, Facture facture, long prestationId);

  public Facture findById(long id);

  public Facture findByNumero(String numero);

  public List<Facture> findAllByPrestation(String siret, long idPrestation);
  
  public List<Facture> findAllBySiret(String siret);
}

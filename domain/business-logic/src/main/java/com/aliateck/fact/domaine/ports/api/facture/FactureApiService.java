package com.aliateck.fact.domaine.ports.api.facture;

import com.aliateck.fact.domaine.business.object.Facture;
import java.util.List;
import java.util.Map;

public interface FactureApiService {
  public Map<String, Object> addFacture(String siret, Facture factureRequest, Long prestationId);

  public void deleteFacture(String siret,Facture facture, Long prestationId);
  
  public void deleteById(String siret, Long idPrestation, Long idFacture);

  public Facture updateFacture(String siret, Facture facture, Long prestationId);

  public Facture findById(Long id);

  public Facture findByNumero(String numero);

  public List<Facture> findAllByPrestation(String siret, Long idPrestation);
  
  public List<Facture> findAllBySiret(String siret);
}

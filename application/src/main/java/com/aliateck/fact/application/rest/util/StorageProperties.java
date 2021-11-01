package com.aliateck.fact.application.rest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class StorageProperties {

  @Value("${storage-pathRoot-pdf}")
  private String pathRoot;

  @Value("${save-file-local-disque}")
  private boolean saveFileLocalDisque;


  @Value("${fichier_suivi_name}")
  private String fichierSuiviFactures;
  
  @Value("${storage-pathRoot-libelle-facture}")
  private String pathLibelleFacture;
  
  @Value("${storage-pathRoot-libelle-charges}")
  private String pathLibelleCharges;
  
  @Value("${storage-pathRoot-libelle-releve}")
  private String pathLibelleReleve;


  public String getPathLibelleFacture() {
    return pathLibelleFacture;
  }

  public void setPathLibelleFacture(String pathLibelleFacture) {
    this.pathLibelleFacture = pathLibelleFacture;
  }

  public String getPathLibelleCharges() {
    return pathLibelleCharges;
  }

  public void setPathLibelleCharges(String pathLibelleCharges) {
    this.pathLibelleCharges = pathLibelleCharges;
  }

 
  public String getPathLibelleReleve() {
    return pathLibelleReleve;
  }

  public void setPathLibelleReleve(String pathLibelleReleve) {
    this.pathLibelleReleve = pathLibelleReleve;
  }

  public String getPathRoot() {
    return pathRoot;
  }

  public void setPathRoot(String pathRoot) {
    this.pathRoot = pathRoot;
  }

  /**
   * @return the saveFileLocalDisque
   */
  public boolean saveFileLocalDisque() {
    return saveFileLocalDisque;
  }

  /**
   * @param saveFileLocalDisque the saveFileLocalDisque to set
   */
  public void setSaveFileLocalDisque(boolean saveFileLocalDisque) {
    this.saveFileLocalDisque = saveFileLocalDisque;
  }



  /**
   * @return the fichierSuiviFactures
   */
  public String getFichierSuiviFactures() {
    return fichierSuiviFactures;
  }

  /**
   * @param fichierSuiviFactures the fichierSuiviFactures to set
   */
  public void setFichierSuiviFactures(String fichierSuiviFactures) {
    this.fichierSuiviFactures = fichierSuiviFactures;
  }

  


}

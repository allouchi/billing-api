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

  /**
   * @return the saveFileLocalDisque
   */
  public boolean isSaveFileLocalDisque() {
    return saveFileLocalDisque;
  }



}

package com.aliateck.fact.infrastructure.repository.edition;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.util.Utils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Slf4j
@Service
public class EditionReportImpl implements EditionReportService {

  private static final String TYPE_FILE = ".pdf";
  private static final String IBAN = "IBAN: ";
  private static final String BIC = "BIC: ";
  private static final String FACTURE_LIBELLE = "Facture";
  private static final String ESPACE_BLANC = " ";
  private static final String UNDERSCORE = "_";
  private static final String RETURN = "\n";

  @Override
  public Map<String, Object> buildParamJasper(Company company, boolean templateChoice,
      Prestation prestation, Facture facture) {

    // infos company
    String rsCompany = company.getSocialReason();
    String statutCompany = company.getStatus();
    Adresse adresseCompany = company.getCompanyAdresse();
    String adresseCompleteCompany = adresseCompany.getNumero() + " " + adresseCompany.getRue()
        + "\n" + adresseCompany.getCodePostal() + " " + adresseCompany.getLocalite() + "\n"
        + adresseCompany.getPays();
    String numeroRcs = company.getRcsName();
    String numeroSiret = company.getSiret().trim();
    String numeroApe = company.getCodeApe();
    String numeroTva = company.getNumeroTva().trim();
    String numeroBic = BIC + company.getNumeroBic();
    String numeroIban = company.getNumeroIban();

    // IBAN: FR17 2004 1010 1254 0796 1J03 367
    // IBAN FR33 3000 2008 9700 0000 5896 J14

    String ibanAffichage = IBAN + numeroIban.substring(0, 4) + ESPACE_BLANC
        + numeroIban.substring(4, 8) + ESPACE_BLANC + numeroIban.substring(8, 12) + ESPACE_BLANC
        + numeroIban.substring(12, 16) + ESPACE_BLANC + numeroIban.substring(16, 20) + ESPACE_BLANC
        + numeroIban.substring(20, 24) + ESPACE_BLANC + numeroIban.substring(24, 27);

    String siretAffichage = null;
    String tvaAffichage = null;
    if (numeroSiret != null) {
      siretAffichage =
          numeroSiret.substring(0, 3) + ESPACE_BLANC + numeroSiret.substring(3, 6) + ESPACE_BLANC
              + numeroSiret.substring(6, 9) + ESPACE_BLANC + numeroSiret.substring(9, 13);

    }

    if (numeroTva != null) {
      tvaAffichage = numeroTva.trim().substring(0, 2) + ESPACE_BLANC
          + numeroTva.trim().substring(2, 5) + ESPACE_BLANC + numeroTva.trim().substring(5, 8)
          + ESPACE_BLANC + numeroTva.trim().substring(8, 11) + ESPACE_BLANC
          + numeroTva.trim().substring(11, 13);
    }

    // infos factures
    String dateFacturation = facture.getDateFacturation();
    String numeroFacture = facture.getNumeroFacture();
    float montantHT = facture.getPrixTotalHT();
    float montantTTC = facture.getPrixTotalTTC();
    float montantTva = facture.getMontantTVA();
    float quantite = facture.getQuantite();
    String moisPrestation = facture.getMoisFacture();
    String communeDateEdition = adresseCompany.getLocalite() + ", le " + dateFacturation;

    String designationLigne1 = "";
    String designationLigne2 = "";
    // infos prestation
    float tarifHT = prestation.getTarifHT();
    String numeroCommande = prestation.getNumeroCommande();
    long delaiPaiement = prestation.getDelaiPaiement();
    String clientPrestation = prestation.getClientPrestation();
    String designation = prestation.getDesignation();
    String consultantFonction = prestation.getConsultant().getFonction();
    String consultantIdentite = prestation.getConsultant().getFirstName() + ESPACE_BLANC
        + prestation.getConsultant().getLastName().toUpperCase();
    String article = null;
    if(moisPrestation != null && (moisPrestation.startsWith("O") || moisPrestation.startsWith("A")) ) {
      article = "Mois d'";
    }else {
      article = "Mois de ";
    }
    if (!templateChoice) {
      designationLigne1 =
          "Prestation pour" + ESPACE_BLANC + clientPrestation.toUpperCase();
      designationLigne2 = article + moisPrestation + ESPACE_BLANC + "par " + consultantIdentite;
    }

    // infos client
    Adresse adresseClient = prestation.getClient().getAdresseClient();
    String adresseCompleteClient = adresseClient.getNumero() + ESPACE_BLANC + adresseClient.getRue()
        + RETURN + adresseClient.getCodePostal() + ESPACE_BLANC + adresseClient.getLocalite()
        + RETURN + adresseClient.getPays();
    String rsClient = prestation.getClient().getSocialReason();
    // Facture_ALIATECK_FINAXYS_Prestation-SG_12-2020_1000.pdf
    String moisFacture = Utils.buildMoisFacture(facture.getMoisFacture());
    String fileName =
        FACTURE_LIBELLE + UNDERSCORE + formatString(rsCompany) + UNDERSCORE + formatString(rsClient)
            + UNDERSCORE + moisFacture + UNDERSCORE + numeroFacture.split("-")[1] + TYPE_FILE;

    // - Parametres envoyes au rapport
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("rs_company", rsCompany);
    parameters.put("statut_company", statutCompany);
    parameters.put("adresse_company", adresseCompleteCompany);
    parameters.put("numero_rcs", numeroRcs);
    parameters.put("numero_siret", siretAffichage);
    parameters.put("numero_tva", tvaAffichage);
    parameters.put("numero_ape", numeroApe);
    parameters.put("code_iban", ibanAffichage);
    parameters.put("code_bic", numeroBic);
    parameters.put("date_facturation", dateFacturation);
    parameters.put("rs_client", rsClient);
    parameters.put("adresse_client", adresseCompleteClient);
    parameters.put("mois_facture", moisPrestation);
    parameters.put("numero_commande", numeroCommande);
    parameters.put("quantite", quantite);
    parameters.put("montantHT", montantHT);
    parameters.put("montantTTC", montantTTC);
    parameters.put("montantTva", montantTva);
    parameters.put("tarifHT", tarifHT);
    parameters.put("numero_facture", numeroFacture);
    parameters.put("commune_company", communeDateEdition);
    parameters.put("designation", designation);
    parameters.put("delai_paiement", delaiPaiement);
    parameters.put("client_prestation", clientPrestation);
    if (!templateChoice) {
      parameters.put("designation_ligne1", designationLigne1);
      parameters.put("designation_ligne2", designationLigne2);
    } else {
      parameters.put("designation", designation);
    }

    parameters.put("fonction_consultant", consultantFonction);
    parameters.put("fileName", fileName);
    return parameters;
  }

  @Override
  public byte[] buildPdfFacture(Map<String, Object> paramJasper, boolean templateChoice,
      String path, boolean storeFile) throws JRException, IOException {
    byte[] file = null;
    try {
      File templateFile = null;
      Map<String, File> mapFiles = Utils.loadFilesResources();
      String outputFileName = (String) paramJasper.get("fileName");
      if (templateChoice) {
        templateFile = mapFiles.get("Custom");
      } else {
        templateFile = mapFiles.get("Default");
      }
      JasperReport jasperDesign = JasperCompileManager.compileReport(templateFile.getPath());
      JRDataSource dataSource = new JREmptyDataSource();
      // - Execution du rapport
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, paramJasper, dataSource);
      // - Creation du rapport au format PDF
      if (storeFile) {
        JasperExportManager.exportReportToPdfFile(jasperPrint,
            path + File.separator + outputFileName);
      }

      // JasperExportManager.exportReportToXmlFile(path+"\\", outputFileName, false);
      file = JasperExportManager.exportReportToPdf(jasperPrint);
      log.info("********************* Fin génération du fichier pdf *********************");

    } catch (Exception e) {
      log.info("Problème lors du chargement des fichiers templates");
      throw e;
    }
    return file;

  }

  @Override
  public void buildSuiviFactures(List<Facture> factures, String path) {


    InputStream targetStream = null;
    EditionSuiviFactures editionFacture = new EditionSuiviFactures();
    try {
      Map<String, File> resources = Utils.loadFilesResources();
      File templateSuivi = resources.get("Suivi");
      targetStream = new FileInputStream(templateSuivi);
      editionFacture.build(factures, targetStream, path);

    } catch (Exception e) {
      log.info("Problème lors de la création du fichier Excel : " + e.getMessage());
    } finally {
      if (targetStream != null) {
        try {
          targetStream.close();
        } catch (IOException e) {
          log.info("Problème lors de la fermeture du fichier Excel : " + e.getMessage());
        }
      }
    }

  }

  private String formatString(String s) {
    String format = s.split(" ")[0];
    String start = format.substring(0, 1);
    format = start.toUpperCase() + format.substring(1).toLowerCase();
    return format;

  }

}

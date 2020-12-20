package com.aliateck.fact.domaine.common.edition;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class EditionFactureImpl implements EditionFactureService {

	private static final String INPUT_FILE="c:/temp/factureDesign.jrxml";
	private static final String PATH_OUTPUT="c:/temp/";
	private static final String TYPE_FILE=".pdf";
	private static final String FACTURE_LIBELLE="FACTURE ";
	
  @Override
  public void editerFacture(Company company, Prestation prestation, Facture facture) {
    try {
    	
    	 JasperReport jasperDesign = JasperCompileManager.compileReport(INPUT_FILE);
         JRDataSource dataSource = new JREmptyDataSource();
         
    	// infos company
    	String rsCompany = company.getSocialReason();
    	String statutCompany = company.getStatus();
    	Adresse adresseCompany = company.getCompanyAdresse();
    	String adresseCompleteCompany = adresseCompany.getNumero() + " " + adresseCompany.getVoie() + "\n"    			
    			+ adresseCompany.getCodePostal() + " " + adresseCompany.getCommune() + "\n"
    			+ adresseCompany.getPays();
    	String numeroRcs = company.getRcsName();
    	String numeroSiret = company.getSiret();
    	String numeroApe = company.getApe();
    	String numeroTva = company.getNumeroTva();    	
    	String siretFormat = numeroSiret.substring(0, 3) + " " + numeroSiret.substring(3,6) + " " + numeroSiret.substring(6, 9) + " " + numeroSiret.substring(9, 14);
    	
    	// infos factures
    	String dateFacturation = facture.getDateFacturation();
    	String numeroFacture = facture.getNumeroFacture();
    	String moisPrestation = facture.getMoisFacture();
    	float montantHT= facture.getPrixTotalHT();
    	float montantTTC = facture.getPrixTotalTTC();
    	float nbJoursEffectues = facture.getNbJoursEffectues();
    	float montantTva = facture.getMontantTVA();
    	String communeDateEdition = adresseCompany.getCommune() + ", le " + dateFacturation;
    	
    	// infos prestation
    	String numeroCommande = prestation.getNumeroCommande();
    	float tarifHT = prestation.getTarifHT();    	    	
    	
    	// infos client
    	Adresse adresseClient = prestation.getClient().getAdresseClient();    	
    	String adresseCompleteClient = adresseClient.getNumero() + " " + adresseClient.getVoie() + "\n"    			
    			+ adresseClient.getCodePostal() + " " + adresseClient.getCommune() + "\n"
    			+ adresseClient.getPays();    	
    	String rsClient = prestation.getClient().getSocialReason();     

      // - ParamÃ¨tres Ã  envoyer au rapport
      Map<String, Object> parameters = new HashMap<>();
      parameters.put("rs_company", rsCompany);
      parameters.put("statut_company", statutCompany);
      parameters.put("adresse_company", adresseCompleteCompany);
      parameters.put("numero_rcs", numeroRcs);
      parameters.put("numero_siret", siretFormat);
      parameters.put("numero_tva", numeroTva);
      parameters.put("numero_ape", numeroApe);
      parameters.put("date_facturation", dateFacturation);
      parameters.put("rs_client", rsClient);
      parameters.put("adresse_client", adresseCompleteClient);
      parameters.put("mois_facture", moisPrestation);
      parameters.put("numero_commande", numeroCommande);
      parameters.put("quantite", nbJoursEffectues);
      parameters.put("montantHT", montantHT);
      parameters.put("montantTTC", montantTTC);
      parameters.put("montantTva", montantTva);
      parameters.put("tarifHT", tarifHT);
      parameters.put("numero_facture", numeroFacture);
      parameters.put("commune_company", communeDateEdition );      
     
      String nameCompany[] = rsCompany.split(" ");
      String libelleFichierFacture = FACTURE_LIBELLE 
      +  nameCompany[0] + " - " + rsClient + " de " + moisPrestation + " " 
    		  + numeroFacture.substring(0, 4) + " - " + numeroFacture.substring(9, 13);
      
      // - ExÃ©cution du rapport
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, parameters, dataSource);      
      // - CrÃ©ation du rapport au format PDF      
      JasperExportManager.exportReportToPdfFile(jasperPrint, PATH_OUTPUT+libelleFichierFacture+TYPE_FILE);     
      
     
    } catch (JRException e) {
      e.printStackTrace();
    }
  }
 
}

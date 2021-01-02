package com.aliateck.fact.infrastructure.repository.edition;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;

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
	
	private static final String INPUT_FILE="c:/temp/factureDesign.jrxml";
	private static final String PATH_OUTPUT="c:/temp/";
	private static final String TYPE_FILE=".pdf";
	private static final String FACTURE_LIBELLE="FACTURE ";
	private static final String ESPACE_BLANC=" ";
	private static final String TIRET=" - ";

  @Override
  public  byte[]  editerFacture(Company company, Prestation prestation, Facture facture) {	
	  
	   byte [] pdfOfByte= null;
	 
	    try {    	 
	    	 
	    	 JasperReport jasperDesign = JasperCompileManager.compileReport(INPUT_FILE);
	         JRDataSource dataSource = new JREmptyDataSource();         
	         
	    	// infos company
	    	String rsCompany = company.getSocialReason();
	    	String statutCompany = company.getStatus();
	    	Adresse adresseCompany = company.getCompanyAdresse();
	    	String adresseCompleteCompany = adresseCompany.getNumero() + " " + adresseCompany.getRue() + "\n"    			
	    			+ adresseCompany.getCodePostal() + " " + adresseCompany.getLocalite() + "\n"
	    			+ adresseCompany.getPays();
	    	String numeroRcs = company.getRcsName();
	    	String numeroSiret = company.getSiret();
	    	String numeroApe = company.getApe();
	    	String numeroTva = company.getNumeroTva();    	
	    	
	    	// infos factures
	    	String dateFacturation = facture.getDateFacturation();
	    	String numeroFacture = facture.getNumeroFacture();
	    	String moisPrestation = facture.getMoisFacture();
	    	float montantHT= facture.getPrixTotalHT();
	    	float montantTTC = facture.getPrixTotalTTC();    	
	    	float montantTva = facture.getMontantTVA();
	    	float quantite = facture.getQuantite();
	    	String communeDateEdition = adresseCompany.getLocalite() + ", le " + dateFacturation;     	
	    	String designation = facture.getDesignation();
	    	 
	    	// infos prestation
	    	float tarifHT = prestation.getTarifHT();  
	    	String numeroCommande = prestation.getNumeroCommande();  	
	    	long delaiPaiement  = prestation.getDelaiPaiement();
	    	// infos client
	    	Adresse adresseClient = prestation.getClient().getAdresseClient();    	
	    	String adresseCompleteClient = adresseClient.getNumero() + " " + adresseClient.getRue() + "\n"    			
	    			+ adresseClient.getCodePostal() + " " + adresseClient.getLocalite() + "\n"
	    			+ adresseClient.getPays();    	
	    	String rsClient = prestation.getClient().getSocialReason();    	

	      // - ParamÃ¨tres Ã  envoyer au rapport
	      Map<String, Object> parameters = new HashMap<>();
	      parameters.put("rs_company", rsCompany);
	      parameters.put("statut_company", statutCompany);
	      parameters.put("adresse_company", adresseCompleteCompany);
	      parameters.put("numero_rcs", numeroRcs);
	      parameters.put("numero_siret", numeroSiret);
	      parameters.put("numero_tva", numeroTva);
	      parameters.put("numero_ape", numeroApe);
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
	      parameters.put("commune_company", communeDateEdition );  
	      parameters.put("designation", designation);
	      parameters.put("delai_paiement", delaiPaiement);
	     
	      String nameCompany[] = rsCompany.split(" ");     
	      String libelleFichierFacture = FACTURE_LIBELLE 
	      +  nameCompany[0] + TIRET + rsClient + " de " + moisPrestation + ESPACE_BLANC + numeroFacture.substring(0, 4) + TIRET
	    		  + numeroFacture.split("-")[1];
	      
	      // - ExÃ©cution du rapport
	      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, parameters, dataSource);      
	      // - CrÃ©ation du rapport au format PDF      
	      JasperExportManager.exportReportToPdfFile(jasperPrint, PATH_OUTPUT+libelleFichierFacture+TYPE_FILE); 
	      
	       pdfOfByte = JasperExportManager.exportReportToPdf(jasperPrint);
	       log.info("********************* Fin de la génération du fichier pdf *********************");
	     
	    } catch (JRException e) {
	    	log.info("Problème lors de la génération du fichier pdf : "+ e.getMessage());
	    } 	    
	   
	    return pdfOfByte;
	  }	 
  
}
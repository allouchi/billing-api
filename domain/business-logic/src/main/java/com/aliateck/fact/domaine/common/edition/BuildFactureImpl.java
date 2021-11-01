package com.aliateck.fact.domaine.common.edition;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.util.FactureStatus;
import com.aliateck.util.Utils;
import groovy.util.logging.Slf4j;

@Service
@Slf4j
public class BuildFactureImpl implements BuildFactureService {
  
  private static final String TIRET= "-";
  
	
	/*
	 *
	 */

	@Override
	public Facture buildFacture(String siret, Prestation prestation, String moisFacture) {
		Facture facture = new Facture();
		if (prestation != null) {
			float tarifHT = prestation.getTarifHT();
			float prixTotalHT = tarifHT * prestation.getQuantite();
			float tva = prixTotalHT * 0.2f;
			facture.setPrixTotalHT(prixTotalHT);
			facture.setPrixTotalTTC(prixTotalHT + tva);
			facture.setMontantTVA(tva);
			facture.setDelaiPaiement(prestation.getDelaiPaiement());
			facture.setDateFacturation(Utils.convertToDateFromLocalDate(LocalDate.now()));
			facture.setDateEcheance(Utils.calculerDateEcheance(prestation, moisFacture));			
			facture.setFactureStatus(FactureStatus.NON.getCode());
			facture.setStatusDesc(FactureStatus.NON.getDescription());
			facture.setFraisRetard(0);
			facture.setNbJourRetard(0);
			facture.setNumeroCommande(prestation.getNumeroCommande());
			facture.setQuantite(prestation.getQuantite());
			facture.setClientPrestation(prestation.getClientPrestation());
			facture.setMoisFacture(moisFacture);
			return facture;
		}
		return null;
	}

	@Override
	public String buildPathFile(String siret, String pathRoot, String rsClient, String moisFacture, Long moisFactureId) {
	  
		String filePath1 = null;	
		String mois = null;
		
		if(moisFactureId < 10) {
		  mois = "0"+moisFactureId+TIRET;
		}else {
		  mois = moisFactureId+TIRET;
		}
		try {
			final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyy");
			LocalDate dateJour = LocalDate.now();
			String annee = formaterDate.format(dateJour);
						
			String factureClient = pathRoot  + File.separator + annee + File.separator + mois
                + moisFacture + File.separator + "Facture Client";
            Path pathFactureClient = Paths.get(factureClient);
            
            String releve = pathRoot  + File.separator + annee + File.separator + mois
                + moisFacture + File.separator + "Relevé de compte";
            Path pathReleve = Paths.get(releve);  
            
            String charge = pathRoot  + File.separator + annee + File.separator + mois
                + moisFacture + File.separator + "Charges";
            Path pathCharge = Paths.get(charge);  
        
        
			filePath1 = Files.createDirectories(pathFactureClient).toString();
			Files.createDirectories(pathReleve).toString();
			Files.createDirectories(pathCharge).toString();
			
		} catch (IOException e) {
			Log.debug("Probleme lors de la creation du repertoire :" + e.getMessage());
		}
		return filePath1;
	}
}

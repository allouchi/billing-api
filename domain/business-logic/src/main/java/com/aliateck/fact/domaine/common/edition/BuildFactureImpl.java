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
			facture.setDateEcheance(Utils.calculerDateEcheance(prestation));			
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
	public String buildPathFile(String siret, String pathRoot, String rsClient, String moisFacture) {
		String filePath = null;
		try {
			final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyy");
			LocalDate dateJour = LocalDate.now();
			String annee = formaterDate.format(dateJour);
			String directory = pathRoot + File.separator + siret + File.separator + annee + File.separator + rsClient + File.separator
					+ moisFacture + File.separator;
			Path path = Paths.get(directory);
			filePath = Files.createDirectories(path).toString();
		} catch (IOException e) {
			Log.debug("Probleme lors de la creation du repertoire :" + e.getMessage());
		}
		return filePath;
	}
}

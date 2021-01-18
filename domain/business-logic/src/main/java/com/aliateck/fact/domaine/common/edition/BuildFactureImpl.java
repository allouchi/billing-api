package com.aliateck.fact.domaine.common.edition;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.util.FactureStatus;
import com.aliateck.util.UtilsFacture;
import groovy.util.logging.Slf4j;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuildFactureImpl implements BuildFactureService {
	private static final String SLASH = "/";

	/*
	 *
	 */

	@Override
	public Facture buildFacture(String siret, Prestation prestation) {
		Facture facture = new Facture();
		if (prestation != null) {
			float tarifHT = prestation.getTarifHT();
			float prixTotalHT = tarifHT * prestation.getQuantite();
			float tva = prixTotalHT * 0.2f;
			facture.setPrixTotalHT(prixTotalHT);
			facture.setPrixTotalTTC(prixTotalHT + tva);
			facture.setMontantTVA(tva);
			facture.setDelaiPaiement(prestation.getDelaiPaiement());
			facture.setDateFacturation(UtilsFacture.convertToDateFromLocalDate(LocalDate.now()));
			facture.setDateEcheance(UtilsFacture.calculerDateEcheance(prestation));			
			facture.setMoisFacture(UtilsFacture.determinerMoisFacture());
			facture.setFactureStatus(FactureStatus.NON.getCode());
			facture.setFraisRetard(0);
			facture.setNbJourRetard(0);
			facture.setNumeroCommande(prestation.getNumeroCommande());
			facture.setQuantite(prestation.getQuantite());
			facture.setClientPrestation(prestation.getClientPrestation());
			return facture;
		}
		return null;
	}

	@Override
	public String buildPathFile(String siret, String pathRoot, String rsClient) {
		String filePath = null;
		try {
			final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyy");
			LocalDate dateJour = LocalDate.now();
			String annee = formaterDate.format(dateJour);
			String directory = pathRoot + SLASH + siret + SLASH + annee + SLASH + rsClient + SLASH
					+ UtilsFacture.determinerMoisFacture() + SLASH;
			Path path = Paths.get(directory);
			filePath = Files.createDirectories(path).toString();
		} catch (IOException e) {
			Log.debug("Probleme lors de la creation du repertoire :" + e.getMessage());
		}
		return filePath;
	}
}

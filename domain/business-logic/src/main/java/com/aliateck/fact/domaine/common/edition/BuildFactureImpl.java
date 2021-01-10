package com.aliateck.fact.domaine.common.edition;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.FactureStatus;
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
	private static String SLASH = "/";

	/*
	 *
	 */
	
	@Override
	public Facture buildFacture(String siret, Prestation prestation, Facture facture) {
		if (prestation != null && facture != null) {
			float tarifHT = prestation.getTarifHT();
			float prixTotalHT = tarifHT * facture.getQuantite();
			float tva = prixTotalHT * 0.2f;
			facture.setPrixTotalHT(prixTotalHT);
			facture.setPrixTotalTTC(prixTotalHT + tva);
			facture.setMontantTVA(tva);
			facture.setDelaiPaiement(prestation.getDelaiPaiement());
			facture.setDateFacturation(UtilsFacture.convertToDate(LocalDate.now()));
			facture.setDateEcheance(UtilsFacture.calculerDateEcheance(prestation));
			long nbJourRetard = UtilsFacture.calculerNbJourRetard(facture);
			facture.setNbJourRetard(nbJourRetard);
			facture.setFraisRetard(UtilsFacture.calculerFraisRetard(facture));
			facture.setMoisFacture(UtilsFacture.determinerMoisFacture());
			facture.setFactureStatus(FactureStatus.NON.getCode());
			if (facture.getDateEncaissement() != null && !facture.getDateEncaissement().isEmpty()) {
				facture.setFactureStatus(FactureStatus.OUI.getCode());
				facture.setFraisRetard(0);
				facture.setNbJourRetard(0);
			}
			return facture;
		}
		return null;
	}

	@Override
	public String buildPathFile(String siret) {
		String filePath = null;
		try {
			final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyy");
			LocalDate dateJour = LocalDate.now();
			String annee = formaterDate.format(dateJour);
			String directory = siret + SLASH + annee + SLASH + UtilsFacture.determinerMoisFacture() + SLASH;
			Path path = Paths.get(directory);
			filePath = Files.createDirectories(path).toString();
		} catch (IOException e) {
			Log.debug("Probleme lors de la creation du repertoire :" + e.getMessage());
		}
		return filePath;
	}
}

package com.aliateck.fact.infrastructure.adapter.edition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.ports.spi.edition.EditionSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.EntitySpiService;
import com.aliateck.fact.infrastructure.models.FactureEntity;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionSpiAdapter implements EditionSpiService {

	 private static String SLASH ="\\";
	 
	EntitySpiService commonSpiEntityService;
	@Override
	public byte[] downloadPdf(String siret, Long prestationId, Long factureId, String rootDirectory) {
       
		if(siret == null || prestationId == null || prestationId.longValue() == 0 || factureId == null || factureId.longValue() == 0 || rootDirectory == null) {
			throw new IllegalArgumentException("Les variables ne doivent pas être null");
		}
        
		FactureEntity facture = commonSpiEntityService.findFactureById(siret, prestationId, factureId);
		if (facture != null) {
			String path = facture.getFilePath();
			String pathComplet = rootDirectory + SLASH + path;

			try {
				Path pathFile = Paths.get(pathComplet);
				byte [] fileToDecode =  Files.readAllBytes(pathFile);				
				return Base64.getUrlEncoder().encode(fileToDecode);
			} catch (IOException e) {
				log.debug("Pdf not found : " + e.getMessage());
			}
		}
		return new byte[0];
	}
}

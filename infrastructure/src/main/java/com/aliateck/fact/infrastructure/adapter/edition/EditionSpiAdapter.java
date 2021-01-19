package com.aliateck.fact.infrastructure.adapter.edition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.DataPDF;
import com.aliateck.fact.domaine.ports.spi.edition.EditionSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.EntitySpiService;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;

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

	private static final String SLASH = "\\";

	FactureJpaRepository factureJpaRepository;

	@Override
	public DataPDF downloadPdf(Long factureId, String rootDirectory) {

		if (factureId == null || factureId.longValue() == 0 || rootDirectory == null) {
			throw new IllegalArgumentException("Les paramètres ne doivent pas être null");
		}

		Optional<FactureEntity> entity = factureJpaRepository.findById(factureId);

		if (entity.isPresent()) {
			FactureEntity facture = entity.get();
			String path = facture.getFilePath();
			String pathComplet = rootDirectory + SLASH + path;

			try {

				Path pathFile = Paths.get(pathComplet);
				byte[] pdfBinary = Files.readAllBytes(pathFile);
				String fileName = pathFile.getFileName().toString();
				return DataPDF.builder()
						.fileContent(pdfBinary)
						.fileName(fileName)
						.filePath(pathFile).build();

			} catch (IOException e) {
				log.debug("Pdf file not found : " + e.getMessage());
			}
		}
		return null;
	}
}

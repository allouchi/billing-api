package com.aliateck.fact.infrastructure.adapter.edition;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.DataPDF;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.ports.spi.edition.EditionSpiService;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import com.aliateck.util.Utils;

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


	FactureJpaRepository factureJpaRepository;

	@Override
	public DataPDF downloadPdf(Long factureId, String rootDirectory) {

		DataPDF reponse = null;
		if (factureId == null || rootDirectory == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		try {
			Optional<FactureEntity> entity = factureJpaRepository.findById(factureId);

			if (entity.isPresent()) {
				FactureEntity facture = entity.get();
				String path = facture.getFilePath();
				String pathComplet = rootDirectory + File.separator + path;
				Path pathFile = Paths.get(pathComplet);
				byte[] pdfBinary = Files.readAllBytes(pathFile);	
				byte[] encodedBytes = Base64.getEncoder().encode(pdfBinary);
				String fileName = pathFile.getFileName().toString();
				reponse = DataPDF.builder()
						.fileContent(encodedBytes)
						.fileName(fileName)
						.filePath(pathFile)
						.build();				
			}

		} catch (Exception e) {
			log.error("error while getting pdf file : file not found");
			throw new ServiceException(ErrorCatalog.PDF_ERROR, "Un problème est survenu lors du chargement du pdf", e);
		}

		return reponse;
	}
}

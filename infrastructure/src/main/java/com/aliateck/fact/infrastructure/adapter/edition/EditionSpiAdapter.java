package com.aliateck.fact.infrastructure.adapter.edition;

import com.aliateck.fact.domaine.business.object.DataPDF;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.ports.spi.edition.EditionSpiService;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

@Service
//@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditionSpiAdapter implements EditionSpiService {

    FactureJpaRepository factureJpaRepository;

    @Override
    public DataPDF downloadPdf(Long factureId) {

        if (Objects.isNull(factureId)) {
            throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
        }
        try {
            Optional<FactureEntity> entity = factureJpaRepository.findById(factureId);
            entity.orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND));
            FactureEntity facture = entity.get();
            byte[] encodedBytes = Base64.getEncoder().encode(facture.getFileContent());
            return DataPDF.builder().fileContent(encodedBytes).fileName(facture.getFileName()).build();

        } catch (Exception e) {
            log.error("error while getting pdf file : file not found");
            throw new ServiceException(ErrorCatalog.PDF_ERROR,
                    "Un problème est survenu lors du chargement du pdf", e);
        }

    }

}

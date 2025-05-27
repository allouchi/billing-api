package com.sbatec.fact.infrastructure.adapter.batch;

import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.ports.spi.batch.BatchSpiService;
import com.sbatec.fact.infrastructure.mapper.FactureMapper;
import com.sbatec.fact.infrastructure.repository.batch.BatchJpaRepository;
import com.sbatec.util.FactureStatus;
import com.sbatec.util.Utils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
//@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BatchSpiAdapter implements BatchSpiService {

    BatchJpaRepository batchJpaRepository;
    FactureMapper factureMapper;

    @Override
    public List<Facture> findAllFactures() {
        return factureMapper.fromEntityToDomain(batchJpaRepository.findAll());
    }

    @Override
    public void updateFacture(Facture facture) {
        batchJpaRepository.save(factureMapper.fromDomainToEntity(facture));

    }

    @Override
    public Facture calculerFraisRetard(Facture facture) {

        LocalDate dateEcheance = Utils.convertStringToDate(facture.getDateEcheance());
        LocalDate dateJour = LocalDate.now();

        if (facture.getFactureStatus().equalsIgnoreCase(FactureStatus.OUI.getCode())) {
            facture.setFraisRetard(0);
            facture.setNbJourRetard(0);
            return facture;
        }

        if (dateJour.isAfter(dateEcheance)
                && (facture.getDateEncaissement() == null || facture.getDateEncaissement().isEmpty())) {
            long nbJoursRetard = Utils.calculerNbJourRetard(facture);
            float fraisRetard = Utils.calculerFraisRetard(facture, nbJoursRetard);
            facture.setFraisRetard(fraisRetard);
            facture.setNbJourRetard(nbJoursRetard);
        }
        return facture;
    }

}

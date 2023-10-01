package com.aliateck.fact.domaine.adapter.consultant;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.ports.api.consultant.ConsultantApiService;
import com.aliateck.fact.domaine.ports.spi.consultant.ConsultantSpiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultantApiAdapter implements ConsultantApiService {


    ConsultantSpiService consultantSpiService;

    @Override
    public Consultant addConsultant(Consultant consultant, String siret) {
        return consultantSpiService.addConsultant(consultant, siret);
    }

    @Override
    public void deleteConsultant(Long id) {
        consultantSpiService.deleteConsultant(id);
    }

    @Override
    public Consultant updateConsultant(Consultant consultant, String siret) {
        return consultantSpiService.updateConsultant(consultant, siret);
    }

    @Override
    public List<Consultant> findAllBySiret(String siret) {
        return consultantSpiService.findAllBySiret(siret);
    }


    @Override
    public List<Consultant> findAll() {
        return consultantSpiService.findAll();
    }

    @Override
    public Consultant findById(long id) {
        return consultantSpiService.findById(id);
    }
}

package com.aliateck.fact.domaine.adapter.consultant;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.ports.api.consultant.ConsultantApiService;
import com.aliateck.fact.domaine.ports.spi.consultant.ConsultantSpiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

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
  public void deleteConsultant(Consultant consultant) {
    consultantSpiService.removeConsultant(consultant);
  }

  @Override
  public Consultant updateConsultant(Consultant consultant) {
    return consultantSpiService.updateConsultant(consultant);
  }

  @Override
  public List<Consultant> findAll() {
    return consultantSpiService.getAllConsultants();
  }

  @Override
  public Consultant findById(long id) {
    return consultantSpiService.getConsultantById(id);
  }
}

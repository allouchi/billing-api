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
  public void ajouterConsultant(Consultant consultant) {
    consultantSpiService.addConsultant(consultant);
  }

  @Override
  public void supprimerConsultant(Consultant consultant) {
    consultantSpiService.removeConsultant(consultant);
  }

  @Override
  public void mettreAJourConsultant(Consultant consultant) {
    consultantSpiService.updateConsultant(consultant);
  }

  @Override
  public List<Consultant> retournerConsultants() {
    return consultantSpiService.getAllConsultants();
  }

  @Override
  public Consultant chercherConsultantParId(long id) {
    return consultantSpiService.getConsultantById(id);
  }
}

package com.aliateck.fact.domaine.ports.spi.consultant;

import com.aliateck.fact.domaine.business.object.Consultant;
import java.util.List;

public interface ConsultantSpiService {
  public Consultant addConsultant(Consultant consultant, String siret);

  public void removeConsultant(Consultant consultant);

  public Consultant updateConsultant(Consultant consultant);

  public List<Consultant> getAllConsultants();

  public Consultant getConsultantById(long id);
}

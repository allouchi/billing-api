package com.aliateck.fact.domaine.ports.spi.consultant;

import com.aliateck.fact.domaine.business.object.Consultant;
import java.util.List;

public interface ConsultantSpiService {
  public Consultant addConsultant(Consultant consultant, String siret);

  public void deleteConsultant(Long id);

  public Consultant updateConsultant(Consultant consultant, String siret);

  public List<Consultant> findAll(String siret);

  public Consultant findById(Long id);
}

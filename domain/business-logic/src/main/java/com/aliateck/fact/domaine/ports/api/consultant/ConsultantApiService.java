package com.aliateck.fact.domaine.ports.api.consultant;

import com.aliateck.fact.domaine.business.object.Consultant;
import java.util.List;

public interface ConsultantApiService {
  public Consultant addConsultant(Consultant consultant, String siret);

  public void deleteConsultant(Consultant consultant);

  public Consultant updateConsultant(Consultant consultant);

  public List<Consultant> findAll();

  public Consultant findById(long id);
}

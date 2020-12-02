package com.aliateck.fact.domaine.ports.spi.consultant;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Consultant;

public interface ConsultantSpiService {
	
	
	public void addConsultant(Consultant consultant) ;

	public void removeConsultant(Consultant consultant);

	public void updateConsultant(Consultant consultant);

	public List<Consultant> getAllConsultants();

	public Consultant getConsultantById(long id);
	
}


package com.aliateck.fact.domaine.ports.api.consultant;

import java.util.List;

import com.aliateck.fact.domaine.business.object.Consultant;

public interface ConsultantApiService {
	
	public void ajouterConsultant(Consultant consultant) ;

	public void supprimerConsultant(Consultant consultant);

	public void mettreAJourConsultant(Consultant consultant);

	public List<Consultant> retournerConsultants();

	public Consultant chercherConsultantParId(long id);
}


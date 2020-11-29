package com.aliateck.fact.domaine.adapter.bill;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.facture.BillApiService;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import com.aliateck.fact.domaine.ports.spi.user.UserSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillApiAdapter implements BillApiService {
	
	
	FactureSpiService billSpiService;

	@Override
	public void ajouterFacture(Facture bill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerFacture(Facture bill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mettreAJourFacture(Facture bill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Facture chercherFactureParId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Facture chercherFactureParNumero(String numero) {
		// TODO Auto-generated method stub
		return billSpiService.getFactureByNumero(numero);
	}

	@Override
	public List<Facture> chercherFacturesParClient(String numeroClient) {
		//return billSpiService.getAllFactureByClient(numeroClient);
		return null;
		
	}


}

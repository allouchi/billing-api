package com.aliateck.fact;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aliateck.fact.common.facture.FactureStatus;
import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.consultant.ConsultantApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;


@SpringBootApplication
public class ApplicationStarter implements CommandLineRunner{

	  @Autowired
	  private CompanyApiService companyApiService;
	  @Autowired
	  private UserApiService userApiService;
	  @Autowired
	  private ClientApiService clientApiService;
	  @Autowired
	  private ConsultantApiService consultantApiService;
	  
    public static void main(String[] args) {
    	SpringApplication.run(ApplicationStarter.class, args);       
        
    }

@Override 
public void run(String... args) throws Exception{	
	
	
	 User userAdmin = User
		      .builder()
		      .firstName("Admin")
		      .lastName("Admin")
		      .email("admin@hotmail.fr")
		      .password("aaaa")
		      .role("admin")
		      .build(); 
	

		    User userAliatec = User
		      .builder()
		      .firstName("Aliane")
		      .lastName("Khalid")
		      .email("khalid@hotmail.fr")
		      .password("bbbb")
		      .role("write")
		      .build();
		    
		    
	 User userSbatec = User
		      .builder()
		      .firstName("Aliane")
		      .lastName("Mustapha")
		      .email("allouchi@hotmail.fr")
		      .password("aaaa")
		      .role("read")
		      .build();		    

		   
		    
		    List<User> users = new ArrayList<>();		    
		    users.add(userSbatec);
		    users.add(userAliatec);
		    
	 Adresse clientAdresse = Adresse
		      .builder()
		      .voie("Paroi nord de la Grande Arche")
		      .numero("1")
		      .codePostal("92044")
		      .commune("Paris la Defense")
		      .pays("France")
		      .build();

		    Adresse sbatecAdresse = Adresse
		      .builder()
		      .voie("Boulevard National")
		      .numero("111")
		      .codePostal("92500")
		      .commune("Rueil-Malmaison")
		      .pays("France")
		      .build();
		    
		    Adresse aliatecAdresse = Adresse
		    	      .builder()
		    	      .voie("Marcel Dubois")
		    	      .numero("8")
		    	      .codePostal("75012")
		    	      .commune("Paris")
		    	      .pays("France")
		    	      .build();	
		    
		    
		    Consultant consultant1 = Consultant
		    		.builder()
		    		.firstName("Jean")
		    		.lastName("Dubois")
		    		.mail("dubois@gmail.com")
		    		.build();  
		    
		    
		    Consultant consultant2 = Consultant
		    		.builder()
		    		.firstName("Marc")
		    		.lastName("Jean")
		    		.mail("marc@gmail.com")
		    		.build(); 
		    
		    Consultant consultant3 = Consultant
		    		.builder()
		    		.firstName("Gege")
		    		.lastName("Jean")
		    		.mail("gege@gmail.com")
		    		.build(); 
		    
		   
		    
				    
		    Client client = Client
		  	      .builder()
		  	      .adresse(clientAdresse)
		  	      .socialReason("FREELANCE.COM")
		  	      .build();	
		    
	        List<Prestation> prestations = new ArrayList<>();
		   
		    Prestation prestation1 = Prestation    		
		    		.builder()
		    		.tarifHT(500)
		    		.delaiPaiement(30)
		    		.numeroCommande("33962")
		    		.consultant(consultant1)
		    		.client(client)
		    		.build();
		    
		    Prestation prestation2 = Prestation    		
		    		.builder()		    		
		    		.delaiPaiement(60)
		    		.numeroCommande("33967")
		    		.tarifHT(480)
		    		.consultant(consultant2)
		    		.client(client)
		    		.build();
		    
		    Prestation prestation3 = Prestation    		
		    		.builder()
		    		.delaiPaiement(60)
		    		.numeroCommande("33967")
		    		.tarifHT(480)
		    		.consultant(consultant3)
		    		.client(client)
		    		.build();
		    
		    Facture facture1 = Facture
		    		.builder()		    		
		    		.fraisRetard(750f)
		    		.nbJourRetard(5l)
		    		.nbJoursEffectue(2)
		    		.numeroFacture("201907311001")
		    		.factureStatus(FactureStatus.NON.getCode())	
		    		.prestation(prestation1)
		    		.build();
		    
		    Facture facture2 = Facture
		    		.builder()
		    		.fraisRetard(600f)
		    		.nbJourRetard(30l)
		    		.nbJoursEffectue(21)
		    		.numeroFacture("201907311002")
		    		.factureStatus(FactureStatus.OUI.getCode())
		    		.prestation(prestation2)
		    		.build();
		    
		    Facture facture3 = Facture
		    		.builder()
		    		.fraisRetard(600f)
		    		.nbJourRetard(30l)
		    		.numeroFacture("201907311003")
		    		.factureStatus(FactureStatus.OUI.getCode())
		    		.prestation(prestation3)
		    		.build();	    
		   
		    
		   
		   		  
		    Company sbatec = Company
		      .builder()
		      .siret("85292702900011")
		      .rcsName("R.C.S. Nanterre 831 502 141")
		      .socialReason("SBATEC Consulting")
		      .status("SASU au capital de 500 Euros")
		      .tvaName("FR 188 315 021 41")
		      .ape("6201Z")
		      .companyAdresse(sbatecAdresse)
		      .prestation(prestations)
		      .build();	
		    
		    Company aliatec = Company
	      .builder()
	      .siret("85292702900012")
	      .rcsName("R.C.S. Nanterre 831 502 141")
	      .socialReason("ALIATECK")
	      .status("SASU au capital de 500 Euros")
	      .tvaName("FR 188 315 021 41")
	      .ape("6201Z")
	      .companyAdresse(aliatecAdresse)
	      .build();
		    
		    
		   
		    companyApiService.addCompany(sbatec);
		    //companyApiService.addCompany(aliatec);
		   
		      
		
		
	}     

}


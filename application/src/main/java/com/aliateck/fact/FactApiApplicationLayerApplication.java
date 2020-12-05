package com.aliateck.fact;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aliateck.fact.common.FactureStatus;
import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.business.object.ClientAdresse;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.CompanyAdresse;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.consultant.ConsultantApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;


@SpringBootApplication
public class FactApiApplicationLayerApplication implements CommandLineRunner{

	  @Autowired
	  private CompanyApiService companyApiService;
	  @Autowired
	  private UserApiService userApiService;
	  @Autowired
	  private ClientApiService clientApiService;
	  @Autowired
	  private ConsultantApiService consultantApiService;
	  
    public static void main(String[] args) {
    	SpringApplication.run(FactApiApplicationLayerApplication.class, args);       
        
    }

@Override 
public void run(String... args) throws Exception{	
	
	
	 User userSbatec = User
		      .builder()
		      .firstName("Aliane")
		      .lastName("Mustapha")
		      .email("allouchi@hotmail.fr")
		      .password("aaaa")
		      .role("admin")
		      .build();		    

		    User userAliatec = User
		      .builder()
		      .firstName("Aliane")
		      .lastName("Khalid")
		      .email("khalid@hotmail.fr")
		      .password("bbbb")
		      .role("read")
		      .build();
		    
		    List<User> users = new ArrayList<>();		    
		    users.add(userSbatec);
		    users.add(userAliatec);
		    
	 ClientAdresse clientAdresse = ClientAdresse
		      .builder()
		      .voie("Paroi nord de la Grande Arche")
		      .numero("1")
		      .codePostal("92044")
		      .commune("Paris la Defense")
		      .pays("France")
		      .build();

		    CompanyAdresse sbatecAdresse = CompanyAdresse
		      .builder()
		      .voie("Boulevard National")
		      .numero("111")
		      .codePostal("92500")
		      .commune("Rueil-Malmaison")
		      .pays("France")
		      .build();
		    
		    CompanyAdresse aliatecAdresse = CompanyAdresse
		    	      .builder()
		    	      .voie("Marcel Dubois")
		    	      .numero("8")
		    	      .codePostal("75012")
		    	      .commune("Paris")
		    	      .pays("France")
		    	      .build();
		   		    
		    Facture facture1 = Facture
		    		.builder()
		    		.delaiFacturation(60)
		    		.dateEcheance(new Date())
		    		.dateEncaissement(new Date())
		    		.dateFacturation(new Date())
		    		.fraisRetard(60)
		    		.nbJourRetard(60)
		    		.numeroFacture("201907311001")
		    		.factureStatus(FactureStatus.NON.getCode())
		    		.montantHT(500)
		    		.montantTTC(450)
		    		.build();
		    
		    Facture facture2 = Facture
		    		.builder()
		    		.delaiFacturation(60)
		    		.dateEcheance(new Date())
		    		.dateEncaissement(new Date())
		    		.dateFacturation(new Date())
		    		.fraisRetard(60)
		    		.nbJourRetard(60)
		    		.numeroFacture("201907311002")
		    		.factureStatus(FactureStatus.OUI.getCode())
		    		.montantHT(500)
		    		.montantTTC(450)
		    		.build();		    
		    
		    Prestation prestation1 = Prestation    		
		    		.builder()
		    		.nbJoursEffectue(21)
		    		.tarif(500)
		    		.facture(facture1)
		    		.build();
		    
		    Prestation prestation2 = Prestation    		
		    		.builder()
		    		.nbJoursEffectue(20)
		    		.tarif(480)
		    		.facture(facture2)
		    		.build();		   		    
		    
		    List<Consultant> consultants = new ArrayList<>();
		    
		    Consultant consultant1 = Consultant
		    		.builder()
		    		.firstName("Jean")
		    		.lastName("Dubois")
		    		.mail("dubois@gmail.com")
		    		.prestation(prestation1)
		    		.build();  
		    
		    
		    Consultant consultant2 = Consultant
		    		.builder()
		    		.firstName("Marc")
		    		.lastName("Jean")
		    		.mail("marc@gmail.com")
		    		.prestation(prestation2)
		    		.build();  
		    
		    consultants.add(consultant1);
		    consultants.add(consultant2);
		    
		    List<Client> clients = new ArrayList<>();
		    
		    Client client = Client
		  	      .builder()
		  	      .adresse(clientAdresse)
		  	      .socialReason("FREELANCE.COM") 		  	      
		  	      .build();		   
		    
		    clients.add(client);  
		   		  
		    Company sbatec = Company
		      .builder()
		      .siret("85292702900011")
		      .rcsName("R.C.S. Nanterre 831 502 141")
		      .socialReason("SBATEC")
		      .status("SASU au capital de 500 Euros")
		      .tvaName("FR 188 315 021 41")
		      .ape("6201Z")
		      .companyAdresse(sbatecAdresse)
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
		    
		    sbatec.setClients(clients);
		   		    
		    //userApiService.addUser(userSbatec);
		    //
		    
		    
		    sbatec.setConsultant(consultants);
		    companyApiService.addCompany(sbatec);
		    companyApiService.addCompany(aliatec);
		    userApiService.addUser(userSbatec);
		    //userSbatec.setCompany(sbatec);
		   
		
	}     

}


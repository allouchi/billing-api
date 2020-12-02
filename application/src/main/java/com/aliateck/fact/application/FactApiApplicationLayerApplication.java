package com.aliateck.fact.application;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.business.object.ClientAdresse;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.CompanyAdresse;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.consultant.ConsultantApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;


@SpringBootApplication
@ComponentScan(basePackages =  {		
		"com.aliateck.fact.domaine.adapter",
		"com.aliateck.fact.domaine.ports.spi",
		"com.aliateck.fact.domaine.ports.api",
		"com.aliateck.fact.infrastructure.adapter",
		"com.aliateck.fact.infrastructure.mapper", 
		"com.aliateck.fact.application.controllers"})
@EntityScan("com.aliateck.fact.infrastructure.models")
@EnableJpaRepositories("com.aliateck.fact.infrastructure.repository")
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

		   		    
		    Facture facture1 = Facture
		    		.builder()
		    		.delaiFacturation(60)
		    		.dateEcheance(new Date())
		    		.dateEncaissement(new Date())
		    		.dateFacturation(new Date())
		    		.fraisRetard(60)
		    		.nbJourRetard(60)
		    		.numeroFacture("201907311001")
		    		.statusFacture("NOK")
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
		    		.statusFacture("OK")
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
		    		.mail("dubois@gmail.com")
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
		   
		    
		    sbatec.setClients(clients);
		    sbatec.setConsultant(consultants);
		    companyApiService.addCompany(sbatec);
		    
		    //clientApiService.ajouterClient(client);
		    //consultantApiService.ajouterConsultant(consultant);
	
	}   
    

}


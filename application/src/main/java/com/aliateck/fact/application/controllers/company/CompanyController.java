package com.aliateck.fact.application.controllers.company;

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
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import com.aliateck.fact.domaine.ports.spi.client.ClientSpiService;
import com.aliateck.fact.infrastructure.adapter.client.ClientSpiAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
  private CompanyApiService companyApiService;
  private UserApiService userApiService;
  private ClientApiService clientApiService;
  

  @GetMapping(value = "/company")
  public List<Company> getAllCompanys() {
    return companyApiService.getAllCompanys();
  }

  @GetMapping(value = "/company/{reasonSocial}")
  public ResponseEntity<Company> getClient(@PathVariable String reasonSocial) {
    System.out.println(reasonSocial);
    Company company = companyApiService.getCompanyByReasonSocial(reasonSocial);
    return ResponseEntity.ok(company);
  }

  @PostMapping(value = "/company/{reasonSocial}")
  public void addCompany() {
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
      .numero("111")
      .codePostal("75012")
      .commune("Paris")
      .pays("France")
      .build();
    
    Facture facture = Facture
    		.builder()    		
    		.fraisRetard(60)
    		.nbJourRetard(60)
    		.build();
    
    
    Prestation prestation = Prestation    		
    		.builder()
    		.nbJoursEffectue(21)
    		.tarif(500)
    		.delaiPaiement(60)
    		.facture(facture)
    		.build();   
    
    
    User userSbatec = User
      .builder()
      .firstName("Aliane")
      .lastName("Mustapha")
      .email("allouchi@hotmail.fr")
      .password("a")
      .build();   
    

    User userAliatec = User
      .builder()
      .firstName("Aliane")
      .lastName("Khalid")
      .email("khalid@hotmail.fr")
      .password("b")
      .build();
    
    
    
    List<Consultant> consultants = new ArrayList<>();
    
    Consultant consultant = Consultant
    		.builder()
    		.firstName("Jean")
    		.lastName("Dubois")
    		.mail("dubois@gmail.com")    		
    		.build();  
    
    
    List<Client> clients = new ArrayList<>();
    
    Client client1 = Client
  	      .builder()
  	      .adresse(clientAdresse)
  	      .socialReason("FREELANCE.COM 1")       
  	      .build();
    
    Client client2 = Client
    	      .builder()
    	      .adresse(clientAdresse)
    	      .socialReason("FREELANCE.COM 2")       
    	      .build();
    
    clients.add(client1);  
    clients.add(client2); 
  
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
    
   
    
    companyApiService.addCompany(sbatec);
    
    clientApiService.addClient(client1);
    clientApiService.addClient(client2);
    
    sbatec.setClients(clients);
    companyApiService.addCompany(sbatec);
    
    
    
    
    
    
    

//    List<User> usersSbatec = new ArrayList<>();
//    userSbatec.setCompany(sbatec);
//    usersSbatec.add(userSbatec);
//    sbatec.setUsers(usersSbatec);  
   
  
    

//    Company aliatec = Company
//      .builder()
//      .siret("85292702900012")
//      .rcsName("R.C.S. Nanterre 831 502 141")
//      .socialReason("ALIATECK")
//      .status("SASU au capital de 500 Euros")
//      .tvaName("FR 188 315 021 41")
//      .ape("6201Z")
//      .companyAdresse(aliatecAdresse)
//      .build();

    //List<User> usersAliatec = new ArrayList<>();
    //userAliatec.setCompany(sbatec);
    //usersAliatec.add(userAliatec);
    //aliatec.setUsers(usersAliatec);

   
    //companyApiService.addCompany(aliatec);
    
  }
}

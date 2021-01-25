package com.aliateck.fact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.consultant.ConsultantApiService;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.api.prestation.PrestationApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;


@SpringBootApplication
//@Sql({"/employees_schema.sql", "/import_employees.sql"})
public class ApplicationStarter implements CommandLineRunner {
  @Autowired
  private CompanyApiService companyApiService;

  @Autowired
  private UserApiService userApiService;

  @Autowired
  private ClientApiService clientApiService;

  @Autowired
  private ConsultantApiService consultantApiService;

  @Autowired
  private FactureApiService factureApiService;

  @Autowired
  private PrestationApiService prestationApiService;

  public static void main(String[] args) {
    SpringApplication.run(ApplicationStarter.class, args);
  }
  
  @Bean
  public HttpMessageConverter<Object> createXmlHttpMessageConverter()
   {
    final MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
    final XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
    xstreamMarshaller.setAutodetectAnnotations(true);
    xmlConverter.setMarshaller(xstreamMarshaller);
    xmlConverter.setUnmarshaller(xstreamMarshaller);
    return xmlConverter;
   }

  @Override
  public void run(String... args) throws Exception {
    Adresse sbatecAdresse = Adresse
      .builder()
      .rue("Boulevard National")
      .numero("111")
      .codePostal("92500")
      .localite("Rueil-Malmaison")
      .pays("France")
      .build();

    Adresse clientAdresse = Adresse
      .builder()
      .rue("Paroi nord de la Grande Arche")
      .numero("1")
      .codePostal("92044")
      .localite("Paris la Defense")
      .pays("France")
      .build();

    List<Consultant> consultants = new ArrayList<>();
    Consultant consultant = Consultant
      .builder()
      .firstName("Dobois")
      .lastName("Jean")
      .mail("dubois@gmail.com")
      .build();
    consultants.add(consultant);

    List<Client> clients = new ArrayList<>();
    Client client = Client
      .builder()
      .adresseClient(clientAdresse)
      .socialReason("Atos")
      .build();
    clients.add(client);

    List<Prestation> prestations = new ArrayList<>();
    Prestation prestation = Prestation
      .builder()
      .tarifHT(500)
      .delaiPaiement(60l)      
      .consultant(consultant)
      .client(client)
      .build();
    prestations.add(prestation);
    
    Company sbatec = Company
      .builder()
      .socialReason("SBATEC Consulting")
      .status("SASU au capital de 500 Euros")
      .siret("85292702900011")
      .codeApe("6201Z")
      .rcsName("R.C.S. Nanterre 831 502 141")      
      .numeroTva("FR18831502141")
      .codeBic("PSSTFRPPSCE")
      .codeIban("FR1720041010125407961J03367")      
      .companyAdresse(sbatecAdresse)
      .build();
    //companyApiService.addCompany(sbatec);
   
  }
}

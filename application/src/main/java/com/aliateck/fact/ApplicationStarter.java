package com.aliateck.fact;

import com.aliateck.fact.common.facture.FactureStatus;
import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.consultant.ConsultantApiService;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.api.prestation.PrestationApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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

  @Override
  public void run(String... args) throws Exception {
    Adresse sbatecAdresse = Adresse
      .builder()
      .voie("Boulevard National")
      .numero("111")
      .codePostal("92500")
      .commune("Rueil-Malmaison")
      .pays("France")
      .build();

    Adresse clientAdresse = Adresse
      .builder()
      .voie("Paroi nord de la Grande Arche")
      .numero("1")
      .codePostal("92044")
      .commune("Paris la Defense")
      .pays("France")
      .build();

    List<Consultant> consultants = new ArrayList<>();
    Consultant consultant = Consultant
      .builder()
      .firstName("Jean")
      .lastName("Dubois")
      .mail("dubois@gmail.com")
      .build();
    consultants.add(consultant);

    List<Client> clients = new ArrayList<>();
    Client client = Client
      .builder()
      .adresse(clientAdresse)
      .socialReason("FREELANCE.COM")
      .build();
    clients.add(client);

    List<Prestation> prestations = new ArrayList<>();
    Prestation prestation = Prestation
      .builder()
      .tarifHT(500)
      .delaiPaiement(30)
      .numeroCommande("33962")
      .consultant(consultant)
      .client(client)
      .build();
    prestations.add(prestation);

    Facture facture = Facture
      .builder()
      .fraisRetard(750f)
      .nbJourRetard(5l)
      .nbJoursEffectue(21)
      .numeroFacture("201907311001")
      .factureStatus(FactureStatus.NON.getCode())
      .build();

    prestation.setFacture(facture);

    Company sbatec = Company
      .builder()
      .siret("85292702900011")
      .rcsName("R.C.S. Nanterre 831 502 141")
      .socialReason("SBATEC Consulting")
      .status("SASU au capital de 500Euros")
      .tvaName("FR 188 315 021 41")
      .ape("6201Z")
      .companyAdresse(sbatecAdresse)
      .clients(clients)
      .consultants(consultants)
      .prestations(prestations)
      .build();

    Company company = companyApiService.addCompany(sbatec);
    //    Company companyBase = companyApiService.getCompanyById(company.getId());
    //    companyBase.setClients(clients);
    //    companyBase.setConsultants(consultants);
    //    companyBase.setPrestations(prestations);
    //    companyApiService.updateCompany(companyBase);
  }
}

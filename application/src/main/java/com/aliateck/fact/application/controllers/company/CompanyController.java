package com.aliateck.fact.application.controllers.company;

import com.aliateck.fact.domaine.business.object.ClientAdresse;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.CompanyAdresse;
import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import java.util.ArrayList;
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
  CompanyApiService companyApiService;
  UserApiService userApiService;

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

    User user = User
      .builder()
      .firstName("Aliane")
      .lastName("Mustapha")
      .email("allouchi@hotmail.fr")
      .password("a")
      .build();

    //    Client client = Client
    //      .builder()
    //      .adresse(clientAdresse)
    //      .socialReason("FREELANCE.COM")
    //      .build();

    //    List<Client> clients = new ArrayList<>();
    //    clients.add(client);

    Company sbatec = Company
      .builder()
      .siret("85292702900011")
      .rcsName("R.C.S. Nanterre 831 502 141")
      .socialReason("SBATEC")
      .status("SASU au capital de 500 Euros")
      .tvaName("FR 188 315 021 41")
      .ape("6201Z")
      .companyAdresse(sbatecAdresse)
      //.clients(clients)
      //.users(users)
      .build();

    List<User> users = new ArrayList<>();
    user.setCompany(sbatec);
    users.add(user);

    sbatec.setUsers(users);

    Company aliatec = Company
      .builder()
      .siret("85292702900012")
      .rcsName("R.C.S. Nanterre 831 502 141")
      .socialReason("ALIATECK")
      .status("SASU au capital de 500 Euros")
      .tvaName("FR 188 315 021 41")
      .ape("6201Z")
      .companyAdresse(aliatecAdresse)
      //.clients(clients)
      //.users(users)
      .build();

    companyApiService.addCompany(sbatec);
    //Company company = companyApiService.getCompanyById(1L);
    //System.out.println(company);
    // company.setUsers(users);
    //userApiService.addUser(user);
    //companyApiService.updateCompany(company);
  }
}

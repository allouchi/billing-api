package com.aliateck.fact.application;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FactApiApplicationLayerApplicationTests {
  @Autowired
  UserApiService userApiService;

  @Autowired
  CompanyApiService companyApiService;

  @Test
  void contextLoads() {
    Company company = Company
      .builder()
      .siret("85292702900011")
      .rcsName("R.C.S. Nanterre 831 502 141")
      .socialReason("SBATEC Consulting")
      .status("SASU au capital de 500Euros")
      .tvaName("FR 188 315 021 41")
      .ape("6201Z")
      .id(1L)
      .build();

    companyApiService.addCompany(company);

    User user = User
      .builder()
      .firstName("Aliane")
      .lastName("Mustapha")
      .email("allouchi@hotmail.fr")
      .password("a")
      .company(company)
      .build();
    //userApiService.addUser(user);
  }
}

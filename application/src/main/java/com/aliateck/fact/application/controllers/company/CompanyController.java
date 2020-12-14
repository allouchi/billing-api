package com.aliateck.fact.application.controllers.company;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.ports.api.client.ClientApiService;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

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
    Company company = companyApiService.getCompanyByReasonSocialIgnoreCase(reasonSocial);
    return ResponseEntity.ok(company);
  }

  @PostMapping(value = "/company/{reasonSocial}")
  public void addCompany() {
    
    
  }
}

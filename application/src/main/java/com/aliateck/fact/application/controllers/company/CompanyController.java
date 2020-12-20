package com.aliateck.fact.application.controllers.company;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
  private CompanyApiService companyApiService;
  
  
  @GetMapping(value = "/{siret}")
  public ResponseEntity<Company> getCompany(@PathVariable String siret) {
    log.info("Find company by siret {}", siret);
    Company company = companyApiService.getCompanyBySiret(siret);
    return ResponseEntity.ok(company);
  }
  
  
  @GetMapping
  public ResponseEntity<List<Company>> findAll(){
    log.info("Find all companies");
    return ResponseEntity.ok( companyApiService.getAllCompanys());
  }
  

  @PostMapping
  public Company addCompany(@RequestBody Company companyRequest) {
	 log.info("Create new company");
	 System.out.println(companyRequest.getCompanyAdresse());
	 return companyApiService.addCompany(companyRequest);
  }
}

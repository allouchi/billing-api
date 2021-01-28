package com.aliateck.fact.application.rest.controllers.company;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.application.rest.controllers.common.CommonResource.Resource;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Resource.COMPANIES)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
  private CompanyApiService companyApiService;
  
  
  @GetMapping(value = "/{siret}")
  public ResponseEntity<Company> findBySiret(@PathVariable String siret) {
    log.info("Find company by siret : ", siret);
    Company company = companyApiService.findBySiret(siret);    
    return ResponseEntity.ok(company);
  }
  
  
  @GetMapping
  public ResponseEntity<List<Company>> findAll(){
    log.info("Find all companies");
    return ResponseEntity.ok( companyApiService.findAll());
  }
  

  @PostMapping
  public Company addCompany(@RequestBody Company companyRequest) {
	 log.info("Create new company");	
	 return companyApiService.addCompany(companyRequest);
  }
  @PutMapping
  public Company upfateCompany(@RequestBody Company companyRequest) {
	 log.info("Create new company");	
	 return companyApiService.updateCompany(companyRequest);
  }
  
  @DeleteMapping(value = "/{id}")
  public boolean deleteCompany(
    @PathVariable long id    
  ) {
    log.info("delete company by id :" + id);    
    companyApiService.deleteById(id);
    return true;
  } 
}

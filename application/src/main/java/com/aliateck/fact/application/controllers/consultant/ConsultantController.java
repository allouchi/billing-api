package com.aliateck.fact.application.controllers.consultant;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.consultant.ConsultantApiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultants")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultantController {
  private CompanyApiService companyApiService;
  private ConsultantApiService consultantApiService;

  @GetMapping(value = "/{siret}")
  public ResponseEntity<List<Consultant>> getAllConsultants(@PathVariable String siret) {
	  log.info("get all consultants");
    Company company = companyApiService.findBySiret(siret);
    if(company != null && !company.getConsultants().isEmpty()) {
    	 return ResponseEntity.ok(company.getConsultants());
    }
    return null;
   
  }

  @PostMapping(value = "/{siret}")
  public ResponseEntity<Consultant> addConsultant(
    @RequestBody Consultant consultantRequest,
    @PathVariable String siret
  ) {
    log.info("Create new consultant");
    return ResponseEntity.ok(
      consultantApiService.addConsultant(consultantRequest, siret)
    );
  }
  
  @PutMapping(value = "/{siret}")
  public ResponseEntity<Consultant> updateConsultant(
    @RequestBody Consultant consultantRequest,
    @PathVariable String siret
  ) {
    log.info("Update consultant by id : " + consultantRequest.getId());    
    return ResponseEntity.ok( consultantApiService.updateConsultant(consultantRequest, siret));    
  }
  
  @DeleteMapping(value = "/{siret}/{consultantId}")
  public void deleteConsultant(
    @PathVariable Long consultantId,
    @PathVariable String siret  
  ) {
    log.info("delete consultant by id :" + consultantId);    
    consultantApiService.deleteById(consultantId, siret);    
  } 
}

package com.aliateck.fact.application.controllers.consultant;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultant")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultantController {
  private CompanyApiService companyApiService;

  @GetMapping(value = "/{siret}")
  public ResponseEntity<List<Consultant>> getAllConsultants(@PathVariable String siret) {
    System.out.println("**********************************: " + siret);
    Company company = companyApiService.getCompanyBySiret(siret);
    return ResponseEntity.ok(company.getConsultants());
  }
}

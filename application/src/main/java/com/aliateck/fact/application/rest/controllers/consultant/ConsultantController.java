package com.aliateck.fact.application.rest.controllers.consultant;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.ports.api.consultant.ConsultantApiService;
import com.aliateck.util.CommonResource;
import com.aliateck.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(Resource.CONSULTANTS)
public class ConsultantController implements CommonResource {

  private ConsultantApiService consultantApiService;

  // @Secured(value = { "ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ" })
  @GetMapping(value = "/{siret}")
  public List<Consultant> getAllConsultants(@PathVariable String siret) {
    log.info("get all consultants");
    return consultantApiService.findAll(siret);
  }

  // @Secured(value = { "ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ" })
  @PostMapping(value = "/{siret}")
  public Consultant addConsultant(@RequestBody Consultant consultantRequest,
      @PathVariable String siret) {
    log.info("Create new consultant");
    return consultantApiService.addConsultant(consultantRequest, siret);
  }

  // @Secured(value = { "ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ" })
  @PutMapping(value = "/{siret}")
  public Consultant updateConsultant(@RequestBody Consultant consultantRequest,
      @PathVariable String siret) {
    log.info("Update consultant by id : " + consultantRequest.getId());
    return consultantApiService.updateConsultant(consultantRequest, siret);

  }

  // @Secured(value = { "ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ" })
  @DeleteMapping(value = "/{siret}/{consultantId}")
  public void deleteConsultant(@PathVariable Long consultantId, @PathVariable String siret) {
    log.info("delete consultant by id :" + consultantId);
    consultantApiService.deleteConsultant(consultantId);
  }
}

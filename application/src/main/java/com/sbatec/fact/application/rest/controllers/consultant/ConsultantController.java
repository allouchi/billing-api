package com.sbatec.fact.application.rest.controllers.consultant;

import com.sbatec.fact.domaine.business.object.Consultant;
import com.sbatec.fact.domaine.ports.api.consultant.ConsultantApiService;
import com.sbatec.util.CommonResource;
import com.sbatec.util.CommonResource.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(Resource.CONSULTANTS)
public class ConsultantController implements CommonResource {

    private ConsultantApiService consultantApiService;

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping(value = "/{siret}")
    public List<Consultant> getAllConsultantsBySiret(@PathVariable @NotNull String siret) {
        log.info("get all consultants by siret : {}", siret);
        return consultantApiService.findAllBySiret(siret);
    }


    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping()
    public List<Consultant> getAllConsultants() {
        log.info("get all consultants");
        return consultantApiService.findAll();
    }


    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @PostMapping(value = "/{siret}")
    public Consultant addConsultant(@RequestBody @NotNull Consultant consultantRequest,
                                    @PathVariable @NotNull String siret) {
        log.info("Create new consultant");
        return consultantApiService.addConsultant(consultantRequest, siret);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @PutMapping(value = "/{siret}")
    public Consultant updateConsultant(@RequestBody @NotNull Consultant consultantRequest,
                                       @PathVariable @NotNull String siret) {
        log.info("Update consultant by id {}:", consultantRequest.getId());
        return consultantApiService.updateConsultant(consultantRequest, siret);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @DeleteMapping(value = "/{consultantId}")
    public void deleteConsultant(@PathVariable @NotNull Long consultantId) {
        log.info("delete consultant by id {}:", consultantId);
        consultantApiService.deleteConsultant(consultantId);
    }
}

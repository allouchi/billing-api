package com.sbatec.fact.application.rest.controllers.company;

import com.sbatec.fact.domaine.business.object.Company;
import com.sbatec.fact.domaine.ports.api.company.CompanyApiService;
import com.sbatec.util.CommonResource.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Resource.COMPANIES)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {

    CompanyApiService companyApiService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/{siret}")
    public Company findBySiret(@PathVariable String siret) {
        log.info("Find company by siret : {}", siret);
        return companyApiService.findBySiret(siret);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EDITOR')")
    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        log.info("Find all companies");
        List<Company> companies = companyApiService.findAll();
        return ResponseEntity.ok(companies);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping
    public Company addCompany(@RequestBody Company companyRequest) {
        log.info("Create new company");
        return companyApiService.addCompany(companyRequest);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PutMapping
    public Company updateCompany(@RequestBody Company companyRequest) {
        log.info("Update company");
        return companyApiService.updateCompany(companyRequest);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @DeleteMapping(value = "/{id}")
    public void deleteCompany(@PathVariable @NotNull long id) {
        log.info("delete company by id :" + id);
        companyApiService.deleteById(id);
    }
}

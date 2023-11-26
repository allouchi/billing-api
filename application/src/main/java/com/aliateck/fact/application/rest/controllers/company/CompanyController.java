package com.aliateck.fact.application.rest.controllers.company;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(Resource.COMPANIES)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {

    CompanyApiService companyApiService;

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("/{siret}")
    public Company findBySiret(@PathVariable String siret) {
        log.info("Find company by siret : {}", siret);
        return companyApiService.findBySiret(siret);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = "/user/{userName:.+}")
    public List<Company> findByUserName(@PathVariable String userName) {
        log.info("Find company by userName : {}", userName);
        return companyApiService.findByUserName(userName);
    }

    @Secured(value = {"ROLE_ADMIN"})
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

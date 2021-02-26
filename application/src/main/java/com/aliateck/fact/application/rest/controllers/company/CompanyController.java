package com.aliateck.fact.application.rest.controllers.company;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.util.CommonResource.Resource;

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

	CompanyApiService companyApiService;

	@Secured(value = { "ROLE_ADMIN" })
	@GetMapping(value = "/{siret}")
	public ResponseEntity<Company> findBySiret(@PathVariable String siret) {
		log.info("Find company by siret : ", siret);
		Company company = companyApiService.findBySiret(siret);
		return ResponseEntity.ok(company);
	}

	@Secured(value = { "ROLE_ADMIN" })
	@GetMapping(value = "/{userName:.+}/{userName:.+}")
	public ResponseEntity<Company> findByUserName(@PathVariable String userName) {
		log.info("Find company by userName : ", userName);
		Company company = companyApiService.findByUserName(userName);
		return ResponseEntity.ok(company);
	}

	@Secured(value = { "ROLE_ADMIN" })
	@GetMapping
	public ResponseEntity<List<Company>> findAll() {
		log.info("Find all companies");
		return ResponseEntity.ok(companyApiService.findAll());
	}

	@Secured(value = { "ROLE_ADMIN" })
	@PostMapping
	public Company addCompany(@RequestBody Company companyRequest) {
		log.info("Create new company");
		return companyApiService.addCompany(companyRequest);
	}

	@Secured(value = { "ROLE_ADMIN" })
	@PutMapping
	public Company updateCompany(@RequestBody Company companyRequest) {
		log.info("Create new company");
		return companyApiService.updateCompany(companyRequest);
	}

	@Secured(value = { "ROLE_ADMIN" })
	@DeleteMapping(value = "/{id}")
	public boolean deleteCompany(@PathVariable long id) {
		log.info("delete company by id :" + id);
		companyApiService.deleteById(id);
		return true;
	}
}

package com.sbatec.fact.application.rest.controllers.dashboard;


import com.sbatec.fact.domaine.business.object.Operation;
import com.sbatec.fact.domaine.ports.api.operation.OperationApiService;
import com.sbatec.util.CommonResource;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(CommonResource.Resource.OPERATIONS)
public class OperationController {

    OperationApiService operationApiService;

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping(value = "/{siret}")
    public List<Operation> getOperations(@PathVariable @NotNull String siret) {
        log.info("get all opérations by siret {}", siret);
        List<Operation> opertations = operationApiService.findOperations(siret);
        if (opertations != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            opertations.sort((p1, p2) -> {
                LocalDate date1 = LocalDate.parse(p1.getDateOperation(), dateFormatter);
                LocalDate date2 = LocalDate.parse(p2.getDateOperation(), dateFormatter);
                return date2.compareTo(date1);
            });
        }
        return opertations;
    }


    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/add")
    public Operation addOperation(@RequestBody @NotNull Operation operation) {
        log.info("Add operation : {}", operation.getTypeOperation());
        return operationApiService.addOperation(operation);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PutMapping(value = "/edit")
    public Operation editOperation(@RequestBody @NotNull Operation operation) {
        log.info("Edit operation : {}", operation.getTypeOperation());
        return operationApiService.addOperation(operation);
    }


    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteOperation(@PathVariable @NotNull long id) {
        log.info("delete operation by id : " + id);
        operationApiService.deleteOperationById(id);
    }
}

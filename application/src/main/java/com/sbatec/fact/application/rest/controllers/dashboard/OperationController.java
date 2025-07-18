package com.sbatec.fact.application.rest.controllers.dashboard;


import com.sbatec.fact.domaine.business.object.Operation;
import com.sbatec.fact.domaine.ports.api.operation.OperationApiService;
import com.sbatec.util.CommonResource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(CommonResource.Resource.OPERATIONS)
public class OperationController {

    OperationApiService operationApiService;

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping()
    public List<Operation> getOperations() {
        log.info("get all opérations");
        return operationApiService.findOperations();
    }

}

package com.sbatec.fact.application.rest.controllers.user;

import com.sbatec.fact.domaine.business.object.Role;

import com.sbatec.fact.domaine.ports.api.user.RoleApiService;

import com.sbatec.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(Resource.ROLES)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleApiService roleApiService;

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        log.info("Get all role ref");
        List<Role> refs = roleApiService.getAll();
        return ResponseEntity.ok(refs);
    }
}

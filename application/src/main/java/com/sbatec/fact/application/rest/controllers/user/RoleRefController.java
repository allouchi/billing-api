package com.sbatec.fact.application.rest.controllers.user;

import com.sbatec.fact.domaine.business.object.RoleRef;
import com.sbatec.fact.domaine.ports.api.user.RoleRefApiService;
import com.sbatec.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(Resource.ROLES)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleRefController {

    RoleRefApiService roleRefApiService;

    @GetMapping
    public ResponseEntity<List<RoleRef>> getAllRoles() {
        log.info("Get all role ref");
        List<RoleRef> refs = roleRefApiService.getAll();
        return ResponseEntity.ok(refs);
    }

}

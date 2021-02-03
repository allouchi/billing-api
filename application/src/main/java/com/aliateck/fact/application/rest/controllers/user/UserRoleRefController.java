package com.aliateck.fact.application.rest.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.util.CommonResource.Resource;
import com.aliateck.fact.domaine.business.object.UserRoleRef;
import com.aliateck.fact.domaine.ports.api.user.UserRoleRefApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(Resource.ROLES)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRoleRefController {

	UserRoleRefApiService userRoleRefApiService;

	@GetMapping
	public ResponseEntity<List<UserRoleRef>> getAllRoles() {
		log.info("Get all role ref");
		List<UserRoleRef> refs = userRoleRefApiService.getAll();
		return ResponseEntity.ok(refs);
	}

}

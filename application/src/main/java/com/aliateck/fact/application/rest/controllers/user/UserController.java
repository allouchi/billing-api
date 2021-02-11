package com.aliateck.fact.application.rest.controllers.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import com.aliateck.util.CommonResource.Resource;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(Resource.USERS)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

	static final String SPRING_SECURITY_CONTEXT_KEY = "SPRING_SECURITY_CONTEXT";

	UserApiService userApiService;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Secured(value = { "ROLE_ADMIN" })
	@GetMapping(value = "/{mail:.+}")
	public ResponseEntity<User> findByUserName(@PathVariable String userName) {
		log.info("Get user by Email : " + userName);
		User user = userApiService.findByUserName(userName);
		return ResponseEntity.ok(user);
	}

	@Secured(value = { "ROLE_ADMIN" })
	@GetMapping(value = "/allUsers")
	public List<User> getUsers() {
		return userApiService.getUsers();
	}

	@Secured(value = { "ROLE_ADMIN" })
	@PostMapping
	public User addUser(@RequestBody User userReq) {
		log.info("Add user : " + userReq.getUserName());
		// passwordEncoder.encode(userReq.getPassword());
		return userApiService.addUser(userReq);
	}

	@GetMapping(value = "/getUserLogged")
	public ResponseEntity<Map<String, Object>> getLogedUser(HttpServletRequest httpServletRequest) {
		Map<String, Object> params = new HashMap<>();
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext security = (SecurityContext) httpSession.getAttribute(SPRING_SECURITY_CONTEXT_KEY);
		if (security != null && security.getAuthentication() != null) {
			String userName = security.getAuthentication().getName();

			List<String> roles = new ArrayList<>();
			for (GrantedAuthority ga : security.getAuthentication().getAuthorities()) {
				roles.add(ga.getAuthority());
			}
			params.put("username", userName);
			params.put("roles", roles);
		}
		if (params.isEmpty()) {

			return new ResponseEntity(null, HttpStatus.FORBIDDEN);

		}
		return null;

	}
}

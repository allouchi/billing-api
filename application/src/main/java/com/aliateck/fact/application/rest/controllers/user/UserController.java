package com.aliateck.fact.application.rest.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.application.rest.controllers.common.CommonResource.Resource;
import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;

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

	UserApiService userApiService;

	@GetMapping(value = "/{mail:.+}")
	public ResponseEntity<User> findUserByEMail(@PathVariable String mail) {
		log.info("Get user by Email : " + mail);
		User user = userApiService.findUserByMail(mail);
		return ResponseEntity.ok(user);
	}

	@GetMapping(value = "/{mail}/{password}")
	public ResponseEntity<User> findUserByMailAndPassword(@PathVariable String mail, @PathVariable String password) {
		User user = userApiService.findUserByMailAndPassword(mail, password);
		return ResponseEntity.ok(user);
	}

	@GetMapping(value = "/allUsers")
	public List<User> getUsers() {
		return userApiService.getUsers();
	}

	@PostMapping
	public User addUser(@RequestBody User userReq) {
		log.info("Add user : " + userReq.getEmail());
		return userApiService.addUser(userReq);			
	}
}

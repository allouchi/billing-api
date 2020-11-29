package com.aliateck.fact.application.controllers.user;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
  UserApiService userApiService;
  CompanyApiService companyApiService;

  @GetMapping(value = "/user/{mail}/{password}")
  public ResponseEntity<User> findUserByMailAndPassword(
    @PathVariable String mail,
    @PathVariable String password
  ) {
    User user = userApiService.findUserByMailAndPassword(mail, password);
    return ResponseEntity.ok(user);
  }

  @GetMapping(value = "/allUsers")
  public List<User> getUsers() {
    return userApiService.getUsers();
  }

  @PostMapping(value = "/user/{mail}/{password}")
  public void addUser(@PathVariable String mail, @PathVariable String password) {}
}

package com.aliateck.fact.application.controllers.user;

import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.domaine.business.object.Company;
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
  public void addUser(@PathVariable String mail, @PathVariable String password) {
    Adresse sbatecAdresse = Adresse
      .builder()
      .voie("Boulevard National")
      .numero("111")
      .codePostal("92500")
      .commune("Rueil-Malmaison")
      .pays("France")
      .build();

    Company sbatec = Company
      .builder()
      .siret("85292702900011")
      .rcsName("R.C.S. Nanterre 831 502 141")
      .socialReason("SBATEC")
      .status("SASU au capital de 500 Euros")
      .numeroTVA("FR 188 315 021 41")
      .ape("6201Z")
      .companyAdresse(sbatecAdresse)
      .build();

    User user1 = User
      .builder()
      .email(mail)
      .firstName("Aliane")
      .lastName("Mustapha")
      .password(password)
      .build();

    User user2 = User
      .builder()
      .email(mail)
      .firstName("ALIANNE")
      .lastName("Khalid")
      .password(password)
      .build();

    user1.setCompany(sbatec);
    user2.setCompany(sbatec);
    userApiService.addUser(user1);
    userApiService.addUser(user2);
  }
}

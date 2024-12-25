package com.aliateck.fact.application.rest.controllers.user;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import com.aliateck.util.CommonResource.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(Resource.USERS)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    static final String SPRING_SECURITY_CONTEXT_KEY = "SPRING_SECURITY_CONTEXT";

    UserApiService userApiService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Secured(value = {"ADMIN"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/{userName:.+}")
    public User findByUserName(@PathVariable @NotNull String userName) {
        log.info("Get user by Email : " + userName);
        return userApiService.findByUserName(userName);
    }


    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping()
    public List<User> findAllUsers() {
        log.info("Get all users by Email");
        return userApiService.findAllUsers();
    }

    @Secured(value = {"ADMIN"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/{userName:.+}/{password}")
    public User findByUserNameAndPassword(@PathVariable @NotNull String userName,
                                          @PathVariable @NotNull String password) {
        log.info("Get user by Email and password : " + userName);
        return userApiService.findByUserNameAndPassword(userName, password);

    }

    //@Secured(value = {"ADMIN"})
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/")
    public User addUser(@RequestBody @NotNull User user) {
        log.info("Add user : " + user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userApiService.addUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable @NotNull long id) {
        log.info("delete client by id :" + id);
        userApiService.deleteUserById(id);
    }

    @Secured(value = {"ADMIN", "WRITE", "READ"})
    @GetMapping(value = "/getUserLogged")
    public ResponseEntity<Map<String, Object>> getLogedUser(HttpServletRequest httpServletRequest) {
        Map<String, Object> params = new HashMap<>();
        HttpSession httpSession = httpServletRequest.getSession();
        SecurityContext security =
                (SecurityContext) httpSession.getAttribute(SPRING_SECURITY_CONTEXT_KEY);
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
            return new ResponseEntity<>(null);
        }
        return null;
    }
}

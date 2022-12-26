package com.aliateck.fact.application.rest.controllers.user;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import com.aliateck.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
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
    @GetMapping(value = "/{userName:.+}")
    public ResponseEntity<User> findByUserName(@PathVariable @NotNull String userName) {
        log.info("Get user by Email : " + userName);
        User user = userApiService.findByUserName(userName);
        return ResponseEntity.ok(user);
    }

    @Secured(value = {"ADMIN"})
    @GetMapping(value = "/{userName:.+}/{password}")
    public ResponseEntity<User> findByUserNameAndPassword(@PathVariable @NotNull String userName,
                                                          @PathVariable @NotNull String password) {
        log.info("Get user by Email and password : " + userName);
        User user = userApiService.findByUserNameAndPassword(userName, password);
        return ResponseEntity.ok(user);
    }

    @Secured(value = {"ADMIN"})
    @PostMapping
    public User addUser(@RequestBody User userReq) {
        log.info("Add user : " + userReq.getUserName());
        userReq.setPassword(passwordEncoder.encode(userReq.getPassword()));
        return userApiService.addUser(userReq);
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

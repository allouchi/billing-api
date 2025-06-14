package com.sbatec.fact.application.rest.controllers.user;

import com.sbatec.fact.config.auth.JwtService;
import com.sbatec.fact.domaine.business.object.*;
import com.sbatec.fact.domaine.ports.api.company.CompanyApiService;
import com.sbatec.fact.domaine.ports.api.user.RoleApiService;
import com.sbatec.fact.domaine.ports.api.user.UserApiService;
import com.sbatec.util.CommonResource.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(Resource.USERS)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    static final String SPRING_SECURITY_CONTEXT_KEY = "SPRING_SECURITY_CONTEXT";

    UserApiService userApiService;
    private AuthenticationManager authManager;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private CompanyApiService companyApiService;

    private RoleApiService roleApiService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication auth = authManager.authenticate(authToken);
        UserDetails user = (UserDetails) auth.getPrincipal();
        String jwt = jwtService.generateToken(user);

        List<Role> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(role -> role.startsWith("ROLE_"))
                .map(roleName -> {
                    // Supposons que vous avez un service RoleService avec une méthode findByRoleName
                    Role existingRole = roleApiService.findByRoleName(roleName);
                    return Role.builder()
                            .id(existingRole.getId())
                            .description(existingRole.getDescription())
                            .roleName(roleName)
                            .build();
                })
                .collect(Collectors.toList());

        User usr = userApiService.findByUserName(user.getUsername());
        usr.setRoles(roles);
        usr.setPassword("");

        Company company = companyApiService.findBySiret(usr.getSiret());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwt);
        authResponse.setUser(usr);
        authResponse.setCompany(company);
        authResponse.setSocialReason(company.getSocialReason());
        return ResponseEntity.ok(authResponse);
    }


    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        System.out.println();
        return ResponseEntity.ok(null);
    }


    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/{userName:.+}")
    public User findByUserName(@PathVariable @NotNull String userName) {
        log.info("Get user by Email : " + userName);
        return userApiService.findByUserName(userName);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping()
    public List<User> findAllUsers() {
        log.info("Get all users by Email");
        List<User> users = userApiService.findAllUsers();

        if (users != null && !users.isEmpty()) {
            users.forEach(u -> {
                u.setPassword("");
            });
        }
        return users;
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/{email:.+}/{password}")
    public User findByUserNameAndPassword(@PathVariable @NotNull String email,
                                          @PathVariable @NotNull String password) {
        log.info("Get user by Email and password : " + email);
        return userApiService.findByUserNameAndPassword(email, password);

    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/add")
    public User addUser(@RequestBody @NotNull User user) {
        log.info("Add user : " + user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userApiService.addUser(user);
    }


    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @ResponseStatus(code = HttpStatus.CREATED)
    @PutMapping(value = "/edit")
    public User editUser(@RequestBody @NotNull User user) {
        log.info("Add user : {}", user.getEmail());
        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
            User savedUser = userApiService.findUserById(user.getId());
            user.setPassword(savedUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userApiService.addUser(user);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_WRITE", "ROLE_READ"})
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable @NotNull long id) {
        log.info("delete user by id : " + id);
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

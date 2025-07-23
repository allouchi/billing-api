package com.sbatec.fact.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbatec.fact.domaine.exception.ErrorBack;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    @Autowired
    DataSource dataSourceMe;
    @Autowired
    private JwtAuthenticationFilter jwtFilter;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**");
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .anonymous(anonymous -> anonymous.disable())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(
                                "/api/users/login", "/api/users/refresh-token", "/api/users/logout").permitAll()
                        .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());
        http.sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        //http.formLogin(AbstractHttpConfigurer::disable);

        http.formLogin(form -> form
                //.loginProcessingUrl("/api/users/login")
                .failureHandler(authenticationFailureHandler())
                .successHandler(authenticationSuccessHandler())
                .permitAll()
        );
        http.rememberMe((remember) -> remember
                .rememberMeServices(rememberMeServices)
        );
        http.logout(logout -> logout
                .logoutUrl("/api/users/logout")
                .logoutSuccessUrl("/api/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        return http.build();
    }

    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        TokenBasedRememberMeServices.RememberMeTokenAlgorithm encodingAlgorithm = TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("myKey", userDetailsService, encodingAlgorithm);
        rememberMe.setMatchingAlgorithm(TokenBasedRememberMeServices.RememberMeTokenAlgorithm.MD5);
        return rememberMe;
    }

    @Bean
    RememberMeAuthenticationFilter rememberMeFilter() {
        RememberMeAuthenticationFilter rememberMeFilter = new RememberMeAuthenticationFilter();
        rememberMeFilter.setRememberMeServices(rememberMeServices());
        rememberMeFilter.setAuthenticationManager(theAuthenticationManager);
        return rememberMeFilter;
    }

    @Bean
    TokenBasedRememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices();
        rememberMeServices.setUserDetailsService(myUserDetailsService);
        rememberMeServices.setKey("springRocks");
        return rememberMeServices;
    }

    @Bean
    RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        RememberMeAuthenticationProvider rememberMeAuthenticationProvider = new RememberMeAuthenticationProvider();
        rememberMeAuthenticationProvider.setKey("springRocks");
        return rememberMeAuthenticationProvider;
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint() {
        return new Http401UnauthorizedEntryPoint();
    }

    @Bean
    public RestAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler();
    }

    @Bean
    public RestAuthenticationFailureHandler authenticationFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }

    @Bean
    public RestLogoutSuccessHandler logoutSuccessHandler() {
        return new RestLogoutSuccessHandler();
    }

    public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
                throws IOException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "An authentication is required");
        }
    }

    public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) {
            log.debug("On Authentication Success");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
        }
    }

    public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException {

            String code = null;
            String description = null;

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            String error = exception.getClass().getSimpleName();

            switch (error) {
                case "BadCredentialsException":
                    code = "BadCredentialsException";
                    description = "Identifiants incorrects.";
                    break;
                case "DisabledException":
                    code = "DisabledException";
                    description = "Le compte est désactivé.";
                    break;

                case "LockedException":
                    code = "LockedException";
                    description = "Le compte est verrouillé.";
                    break;

                default:
                    code = "BadCredentialsException";
                    description = "Identifiants incorrects.";
                    break;
            }

            ErrorBack errorDetails = new ErrorBack(code, description);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(errorDetails);
            response.getWriter().write(json);
        }
    }

    public class RestLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
            implements LogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

}
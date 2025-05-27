package com.sbatec.fact.config.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**");
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .anonymous(anonymous -> anonymous.disable())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(
                                "/users/login/**", "/users/logout", "/**").permitAll()
                        .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.logout(logout -> logout
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
                throws IOException, ServletException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "An authentication is required");
        }
    }

    public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
            clearAuthenticationAttributes(request);
            log.debug("On Authentication Success");
        }
    }

    public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {
            super.onAuthenticationFailure(request, response, exception);
            log.debug("On Authentication Failure");
        }
    }

    public class RestLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
            implements LogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_OK);
        }
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

}
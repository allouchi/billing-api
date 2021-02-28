package com.aliateck.fact.config.auth;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import com.aliateck.fact.domaine.adapter.user.UserApiDetailAdapter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserApiDetailAdapter userApiDetailsAdapter;

  @Autowired
  public void globalConfig(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider(userApiDetailsAdapter));
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    // http.authorizeRequests().anyRequest().authenticated().antMatchers("/login/**")
    // .hasAnyAuthority("ADMIN", "READ").antMatchers("/delete/**").hasAuthority("ADMIN")
    // .anyRequest().authenticated().and().logout().permitAll().and().exceptionHandling()
    // .accessDeniedPage("/403");

  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider(
      UserApiDetailAdapter userApiDetailsAdapter) {
    this.userApiDetailsAdapter = userApiDetailsAdapter;
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(this.userApiDetailsAdapter);
    authProvider.setPasswordEncoder(getPasswordEncoder());
    return authProvider;
  }

  @Bean
  public static BCryptPasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    return new InMemoryTokenRepositoryImpl();
  }

  @Bean
  public AuthenticationEntryPoint unauthorizedEntryPoint() {
    return (request, response, authException) -> response
        .sendError(HttpServletResponse.SC_UNAUTHORIZED);
  }

  @Bean
  public GrantedAuthorityDefaults grantedAuthorityDefaults() {
    return new GrantedAuthorityDefaults("");
  }

  // Config Remember Me.
  /*
   * http.authorizeRequests().and() //
   * .rememberMe().tokenRepository(this.persistentTokenRepository()) // .tokenValiditySeconds(1 * 24
   * * 60 * 60); // 24h
   * 
   */

  /*
   * auth.jdbcAuthentication().dataSource(source) .usersByUsernameQuery(
   * "select user_name as principal, password as credentials, enabled from T_USER where user_name=?"
   * ) .authoritiesByUsernameQuery(
   * "select user_name as principal, role_name as role from t_role where user_name=?" )
   * .rolePrefix("ROLE_");
   * 
   */

}

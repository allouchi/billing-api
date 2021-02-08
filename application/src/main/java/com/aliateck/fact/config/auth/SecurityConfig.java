package com.aliateck.fact.config.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.aliateck.fact.domaine.ports.api.user.UserApiService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserApiService userDetailsService;

	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource source) throws Exception {
		 auth.jdbcAuthentication()
		.dataSource(source)
		.usersByUsernameQuery("select username as principal, password as credentials, true, from T_USER where user_name=? ")
		.authoritiesByUsernameQuery("select username as principal, password as credentials, true, from T_ROLE where user_name=? ")
		.rolePrefix("ROLE_");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable();
//		.authorizeRequests()
//		.antMatchers(HttpMethod.OPTIONS, "/**")
//		.permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
	}
	
	

}

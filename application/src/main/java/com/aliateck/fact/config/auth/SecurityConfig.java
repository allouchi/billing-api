package com.aliateck.fact.config.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import com.aliateck.fact.infrastructure.adapter.user.UserSpiAdapter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.IGNORED_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserSpiAdapter userDetailsService;

	// @Autowired
	// private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource source) throws Exception {

		auth.jdbcAuthentication().dataSource(source)
				.usersByUsernameQuery(
						"select user_name as principal, password as credentials, enabled from T_USER where user_name=?")
				.authoritiesByUsernameQuery(
						"select user_name as principal, role_name as role from t_role where user_name=?")
				.rolePrefix("ROLE_");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/signin/**")
		.hasRole("ADMIN").and()
		.formLogin();

		// Config Remember Me.
		/*
		 * http.authorizeRequests().and() //
		 * .rememberMe().tokenRepository(this.persistentTokenRepository()) //
		 * .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
		 * 
		 */

	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		return new InMemoryTokenRepositoryImpl();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}

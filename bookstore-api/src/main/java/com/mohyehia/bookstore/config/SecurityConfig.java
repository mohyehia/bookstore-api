package com.mohyehia.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final String[] PUBLIC_ENDPOINTS = {"/api/**/auth/**"};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().and().csrf().disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				.antMatchers(PUBLIC_ENDPOINTS).permitAll()
				.anyRequest().authenticated()
				.and()
			.httpBasic();
	}
}

package com.carros.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic()
			.and()
			.csrf()
			.disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		 
	 	auth
	 	.inMemoryAuthentication()
	 	.passwordEncoder(enconder)
	 	.withUser("guto").password(enconder.encode("guto")).roles("USER")
	 	.and()
	 	.withUser("admin").password(enconder.encode("guto")).roles("USER", "ADMIN");
	}
}
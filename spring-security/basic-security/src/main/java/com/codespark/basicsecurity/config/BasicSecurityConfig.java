package com.codespark.basicsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * 1. Secure HTTP end-points with access roles. 
		 */
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/public/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.httpBasic()
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	/**
	 * Setup for simple in-memory user authentication.
	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("user").password("{noop}password").roles("USER")
//			.and()
//				.withUser("admin").password("{noop}password").roles("ADMIN");
//	}

	/**
	 * 2. Override user details service implementation used by authentication provider.
	 */
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		// User 1 -> USER
		UserDetails user = User.withUsername("user").password(passwordEncoder.encode("pass")).roles("USER").build();

		// User 2 -> ADMIN
		UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("pass")).roles("ADMIN").build();

		// Add the user details
		userDetailsManager.createUser(user);
		userDetailsManager.createUser(admin);

		return userDetailsManager;
	}

}

package com.codespark.springbootbasics.actuator;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.csrf().disable();

		// Secure actuator end points with authenticated user access
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ACTUATOR_ADMIN");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/**
		 * Adding in memory user with the credentials to use basic authentication for
		 * secured end points.
		 * 
		 * USERNAME: admin PASSWORD: password
		 */
		auth.inMemoryAuthentication().withUser("admin")
				.password("{bcrypt}$2a$04$fPsaMC/8QJvinvTKzoxEkuzfJ85BiWP2HyR37go9Vf5tEetlN9Im2")
				.roles("ACTUATOR_ADMIN");
	}

}
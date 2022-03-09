package com.codespark.oauthjwt.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final DataSource dataSource;

	private PasswordEncoder passwordEncoder;
	private UserDetailsService userDetailsService;

	public WebSecurityConfiguration(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * 1. Configure authentication manager builder used by authentication manager.
		 * Setup user details service implementation and password encoder.
		 */
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		/*
		 * 2. Override implementation for user details service.
		 */
		if (userDetailsService == null) {
			userDetailsService = new JdbcDaoImpl();
			((JdbcDaoImpl) userDetailsService).setDataSource(dataSource);
		}
		return userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		/*
		 * 3. Provide password encoder implementation.
		 */
		if (passwordEncoder == null) {
			passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}
		return passwordEncoder;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		/*
		 * 4. Provide authentication manager bean.
		 */
		return super.authenticationManagerBean();
	}

}

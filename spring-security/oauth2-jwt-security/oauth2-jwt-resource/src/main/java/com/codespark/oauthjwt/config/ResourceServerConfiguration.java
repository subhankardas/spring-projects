package com.codespark.oauthjwt.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.codespark.oauthjwt.properties.SecurityProperties;

@Configuration
@EnableResourceServer
@EnableConfigurationProperties(SecurityProperties.class)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String ROOT_PATTERN = "/**";

	private final SecurityProperties securityProperties;

	private TokenStore tokenStore;

	public ResourceServerConfiguration(final SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/*
		 * 1. Configure access rules for secured resources. Setup OAuth access for HTTP
		 * methods using roles.
		 */
		http.authorizeRequests().antMatchers(HttpMethod.GET, ROOT_PATTERN).access("#oauth2.hasScope('read')")
				.antMatchers(HttpMethod.POST, ROOT_PATTERN).access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PATCH, ROOT_PATTERN).access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PUT, ROOT_PATTERN).access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.DELETE, ROOT_PATTERN).access("#oauth2.hasScope('write')");
	}

	@Bean
	public TokenStore tokenStore() {
		/*
		 * 2. Setup JWT token store using token converter.
		 */
		if (tokenStore == null) {
			tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
		}
		return tokenStore;
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		/*
		 * 3. Setup JWT token converter to decode token using public key.
		 */
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setVerifierKey(getPublicKeyAsString());
		return converter;
	}

	/**
	 * Helper function to read JWT public key as string.
	 */
	private String getPublicKeyAsString() {
		try {
			return IOUtils.toString(securityProperties.getJwt().getPublicKey().getInputStream(),
					StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

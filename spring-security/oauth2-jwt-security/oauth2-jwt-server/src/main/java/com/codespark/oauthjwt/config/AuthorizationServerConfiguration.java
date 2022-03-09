package com.codespark.oauthjwt.config;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.codespark.oauthjwt.properties.SecurityProperties;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private final DataSource dataSource;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final SecurityProperties securityProperties;
	private final UserDetailsService userDetailsService;

	private JwtAccessTokenConverter jwtAccessTokenConverter;
	private TokenStore tokenStore;

	public AuthorizationServerConfiguration(final DataSource dataSource, final PasswordEncoder passwordEncoder,
			final AuthenticationManager authenticationManager, final SecurityProperties securityProperties,
			final UserDetailsService userDetailsService) {
		this.dataSource = dataSource;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.securityProperties = securityProperties;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		/*
		 * 1. Configure clients details service using data source.
		 */
		clients.jdbc(this.dataSource);
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		/*
		 * 2. Configure non-security features i.e. authentication manager, JWT token
		 * converter, user details service and token store.
		 */
		endpoints.authenticationManager(this.authenticationManager).accessTokenConverter(jwtAccessTokenConverter())
				.userDetailsService(this.userDetailsService).tokenStore(tokenStore());
	}

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
		/*
		 * 3.Configure security of the authorization server end-points such as
		 * /oauth/token and /oauth/check_token. The password encoder is also configured
		 * here.
		 */
		oauthServer.passwordEncoder(this.passwordEncoder).tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		/*
		 * 1.2. Setup JWT token converter which translates between JWT encoded token
		 * values and OAuth authentication information (in both directions).
		 */
		if (jwtAccessTokenConverter != null) {
			return jwtAccessTokenConverter;
		}

		// Get key-store factory and generate key-pair (private and public key)
		SecurityProperties.JwtProperties jwtProperties = securityProperties.getJwt();
		KeyPair keyPair = keyPair(jwtProperties, keyStoreKeyFactory(jwtProperties));

		// Set key-pair for token converter
		jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setKeyPair(keyPair);
		return jwtAccessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore() {
		/*
		 * 1.4. Setup JWT token store using token converter.
		 */
		if (tokenStore == null) {
			tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
		}
		return tokenStore;
	}

	private KeyPair keyPair(SecurityProperties.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory) {
		return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias(),
				jwtProperties.getKeyPairPassword().toCharArray());
	}

	private KeyStoreKeyFactory keyStoreKeyFactory(SecurityProperties.JwtProperties jwtProperties) {
		return new KeyStoreKeyFactory(jwtProperties.getKeyStore(), jwtProperties.getKeyStorePassword().toCharArray());
	}

}

package com.codespark.springsecuritybasic.config;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    //@formatter:off
    /**
     * Provide bean for JWT parser implementation that will set the signing key and
     * the JWT encoding algorithm. The JWT creation in our case is described by the
     * following algorithm:
     * 
     * JWT = HMACSHA256(BASE64_ENCODE(header) + "." + BASE64_ENCODE(payload), BASE64_ENCODE(secret))
     * 
     */
    @Bean
    public JwtParser jwtParser() {
        Key signingKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret),
                SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parserBuilder().setSigningKey(signingKey).build();
    }
    //@formatter:on

    /**
     * Provide bean for JWT builder implementation that will set the signing key and
     * the JWT signing algorithm.
     */
    @Bean
    public JwtBuilder jwtBuilder() {
        Key signingKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret),
                SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder().signWith(signingKey, SignatureAlgorithm.HS256).setHeaderParam("typ", "JWT");
    }

}

package com.codespark.springsecurityoauth2client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class WebSecurityConfig {

    // ---------------------------------------------------//
    // UNCOMMENT FOR SIMPLE WEB CLIENT METHOD
    // ---------------------------------------------------//
    // @Bean
    // public SecurityWebFilterChain configure(ServerHttpSecurity http) {
    //     http.authorizeExchange(hss -> hss.anyExchange().permitAll());
    //     return http.build();
    // }

    // ---------------------------------------------------//
    // UNCOMMENT FOR OAUTH2 WEB CLIENT METHOD
    // ---------------------------------------------------//
    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        return http.oauth2Client(Customizer.withDefaults()).build();
    }

}
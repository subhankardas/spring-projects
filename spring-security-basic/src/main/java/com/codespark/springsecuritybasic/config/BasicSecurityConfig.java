package com.codespark.springsecuritybasic.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {

    /**
     * Step 5 - User details service bean for auth provider is created using simple
     * in memory user details manager to store user details.
     */
    @Bean
    public UserDetailsService userDetailsServiceInMemory() {
        // Create user details for auth provider
        UserDetails user = User.withUsername("user").password("{noop}password").roles("USER") // simple user
                .accountExpired(false).credentialsExpired(false).disabled(false).build();

        UserDetails admin = User.withUsername("admin").password("{noop}password").roles("ADMIN") // admin user
                .accountExpired(false).credentialsExpired(false).disabled(false).build();

        // Return user details implementation
        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * Step 6 - Security filter chain is created and configured with filter chains
     * for public and private request URL patterns with access roles for different
     * users. Here chaining of the filters are important i.e. filter chain for
     * public requests are handled first allowing all then the private watch list
     * requests allow USER roles and finally for other private requests allow ADMIN
     * roles.
     * Ex.: public/movies/1     -> allow all requests without auth
     *      private/watchlist/1 -> allow USER role
     *      private/users       -> allow ADMIN role
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/public/**").permitAll() // filter chain for public requests, permit all
                        .requestMatchers("/private/watchlist/**").hasRole("USER") // for watch list requests, allow user
                        .requestMatchers("/private/**").hasRole("ADMIN") // for private requests, allow admin users
                        .anyRequest().authenticated()) // any other requests, require authentication
                .httpBasic(withDefaults());

        return http.build();
    }

}

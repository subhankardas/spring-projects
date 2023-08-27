package com.codespark.springsecuritybasic.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codespark.springsecuritybasic.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * STEP 3: Implementation of {@link OncePerRequestFilter} that validates JWT
 * provided in request and sets authenticated user in Spring Security context.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get token from request and validate
        String token = jwtUtils.getToken(request);
        if (token != null && jwtUtils.validateToken(token) && !jwtUtils.isExpired(token)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtils.getUsername(token));

            if (userDetails != null) {
                // Set authenticated user in Spring Security context
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Authenticated request with valid token by '{}'", userDetails.getUsername());
            }
        }

        // Continue request filter chain
        filterChain.doFilter(request, response);
    }

}

package com.codespark.springsecuritybasic.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtParser jwtParser;

    /** Get bearer token from request. */
    public String getToken(HttpServletRequest request) {
        final String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
            return bearerToken.split("Bearer ")[1];
        return null;
    }

    /** Validate token, return true if valid else return false. */
    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                | IllegalArgumentException ex) {
            log.error(ex.getLocalizedMessage());
            return false;
        }
    }

    /** Get username from token. */
    public String getUsername(String token) {
        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }

}

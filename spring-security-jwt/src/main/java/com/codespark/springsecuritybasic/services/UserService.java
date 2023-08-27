package com.codespark.springsecuritybasic.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codespark.springsecuritybasic.data.UserRepository;
import com.codespark.springsecuritybasic.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

/**
 * Provide a custom implementation to override Spring Security's default user
 * details service. The override implementation for loadUserByUsername() method
 * is used to check user details from database during authentication.
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    /**
     * Implement simple authentication based on username and password provided by
     * database. If provided credentials are valid then return JWT token based on
     * user roles. Here we throw UsernameNotFoundException if user is not found
     * which Spring Security automatically uses for un-authenticating user request.
     */
    public String authenticate(String username, String password) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UsernameNotFoundException(username + " not found.");
        }

        List<String> roles = user.getAuthorities().stream().map(role -> role.getAuthority())
                .collect(Collectors.toList());
        return jwtUtils.generateToken(user.getUsername(), roles);
    }

}

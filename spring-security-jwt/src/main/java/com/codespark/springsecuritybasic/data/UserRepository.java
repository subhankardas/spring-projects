package com.codespark.springsecuritybasic.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
public class UserRepository {

    private List<User> USERS = new ArrayList<>();

    public UserRepository(PasswordEncoder passwordEncoder) {
        // Simulate saving users to database
        USERS.add(new User("admin", passwordEncoder.encode("password"), "ADMIN"));
        USERS.add(new User("user", passwordEncoder.encode("password"), "USER"));
    }

    /**
     * Find user by username from database, if not found, throw
     * UsernameNotFoundException.
     */
    public UserDetails findByUsername(String username) {
        return USERS.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .map(user -> getUserDetailsFrom(user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
    }

    /** Maps user from database to UserDetails. */
    public UserDetails getUserDetailsFrom(User user) {
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword()).roles(user.getRole()).build();
    }

}

/**
 * User entity.
 */
@Data
@AllArgsConstructor
class User {

    private String username;
    private String password;
    private String role;

}

package com.codespark.springsecuritybasic.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codespark.springsecuritybasic.services.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/** STEP 5: Add new endpoint to accepts user login credentials. */
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping
    public TokenDTO login(@RequestBody LoginDTO login) {
        return new TokenDTO(userService.authenticate(login.getUsername(), login.getPassword()));
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class LoginDTO {

    private String username;
    private String password;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class TokenDTO {

    private String token;

}
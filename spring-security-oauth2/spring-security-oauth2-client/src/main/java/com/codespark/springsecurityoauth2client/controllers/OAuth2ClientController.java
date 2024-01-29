package com.codespark.springsecurityoauth2client.controllers;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.codespark.springsecurityoauth2client.models.Post;

import reactor.core.publisher.Mono;

@RestController
public class OAuth2ClientController {

    @Autowired
    WebClient client;

    private static final String CLIENT_REG_ID = "client-1";
    private static final String SECURED_RESOURCE_ENDPOINT = "http://localhost:8080/secure/post";

    @GetMapping("/client/post")
    public Mono<Post> index() {
        Mono<Post> res = client.get()
                .uri(SECURED_RESOURCE_ENDPOINT) // Get the secured resource
                .attributes(clientRegistrationId(CLIENT_REG_ID)) // using registered OAuth client
                .retrieve()
                .bodyToMono(Post.class);
        return res;
    }

}

package com.codespark.springsecurityoauth2client.controllers;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.codespark.springsecurityoauth2client.models.Post;
import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@RestController
public class SimpleOAuth2Controller {

    @Value("${spring.security.oauth2.client.registration.client-1.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.client-1.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.client-1.token-uri}")
    private String tokenUri;

    @Autowired
    WebClient client;

    private static Logger logger = LoggerFactory.getLogger(SimpleOAuth2Controller.class);

    private static final String SECURED_RESOURCE_ENDPOINT = "http://localhost:8080/secure/post";

    private static final String GRANT_TYPE = "client_credentials";
    private static final String SCOPES = "user.read user.write";

    @GetMapping("/secure/post")
    public Mono<Post> GetSecuredPost() {
        logger.info("Getting secured post via OAuth2 client credentials manually");
        String basicToken = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        Mono<Post> resource = client.post()
                .uri(tokenUri + "") // Call the oauth2 token endpoint
                .header(HttpHeaders.AUTHORIZATION, basicToken) // with basic token
                .body(BodyInserters
                        .fromFormData(OAuth2ParameterNames.GRANT_TYPE, GRANT_TYPE) // Pass grant type
                        .with(OAuth2ParameterNames.SCOPE, SCOPES)) // and scopes in the body form data
                .retrieve()
                .bodyToMono(JsonNode.class)
                .flatMap(tokenResponse -> {
                    String accessTokenValue = tokenResponse.get("access_token").textValue(); // Get the access token
                    return client.get() // Get the secured resource using the access token
                            .uri(SECURED_RESOURCE_ENDPOINT)
                            .headers(h -> h.setBearerAuth(accessTokenValue + ""))
                            .retrieve()
                            .bodyToMono(Post.class);
                });

        return resource;
    }

}
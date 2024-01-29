package com.codespark.springsecurityoauth2client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

        // ---------------------------------------------------//
        // UNCOMMENT FOR SIMPLE WEB CLIENT METHOD
        // ---------------------------------------------------//
        // @Bean
        // public WebClient webClient() {
        //         return WebClient.builder().build();
        // };

        @Bean
        public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(
                        ReactiveClientRegistrationRepository clientRegistrationRepository,
                        ReactiveOAuth2AuthorizedClientService authorizedClientService) {
                ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider = ReactiveOAuth2AuthorizedClientProviderBuilder
                                .builder()
                                .clientCredentials()
                                .build();

                AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
                                clientRegistrationRepository, authorizedClientService);
                authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

                return authorizedClientManager;
        }

        // ---------------------------------------------------//
        // UNCOMMENT FOR OAUTH2 WEB CLIENT METHOD
        // ---------------------------------------------------//
        @Bean
        public WebClient webClient(ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
                ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
                                authorizedClientManager);
                return WebClient.builder().filter(oauth).build();
        }

}

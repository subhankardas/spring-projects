package com.codespark.springbootrestfulweb.restconsumer;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConsumerConfig {

    /**
     * Spring provides a convenient template class called RestTemplate. RestTemplate
     * makes interacting with most RESTful services a one-line incantation. And it
     * can even bind that data to custom domain types.
     * 
     * @param builder RestTemplateBuilder
     * @return Rest template HTTP client
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}

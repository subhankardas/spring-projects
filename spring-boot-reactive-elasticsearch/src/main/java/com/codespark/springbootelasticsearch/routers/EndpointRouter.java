package com.codespark.springbootelasticsearch.routers;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.codespark.springbootelasticsearch.handlers.EndpointHandler;
import com.codespark.springbootelasticsearch.handlers.GroupSearchHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class EndpointRouter {

    private final EndpointHandler basicHandler;
    private final GroupSearchHandler groupSearchHandler;

    @Bean
    public RouterFunction<ServerResponse> endpointRoutes() {
        return RouterFunctions.route()
                // Basic CRUD operations
                .POST("/endpoints/{machineId}", accept(MediaType.APPLICATION_JSON), basicHandler::saveEndpoint)
                .GET("/endpoints/{machineId}", accept(MediaType.APPLICATION_JSON), basicHandler::getEndpoint)
                .GET("/endpoints", accept(MediaType.APPLICATION_JSON), basicHandler::getEndpoints)

                // Grouping and search operations
                .GET("/endpoints-group", accept(MediaType.APPLICATION_JSON), groupSearchHandler::groupEndpoints)
                .GET("/endpoints-search", accept(MediaType.APPLICATION_JSON), groupSearchHandler::searchEndpoints)

                .build();
    }

}

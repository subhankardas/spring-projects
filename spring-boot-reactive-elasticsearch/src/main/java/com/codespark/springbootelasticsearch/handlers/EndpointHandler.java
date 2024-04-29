package com.codespark.springbootelasticsearch.handlers;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.codespark.springbootelasticsearch.common.Constants;
import com.codespark.springbootelasticsearch.models.Endpoint;
import com.codespark.springbootelasticsearch.services.EndpointService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class EndpointHandler {

    private final EndpointService endpointService;

    public Mono<ServerResponse> saveEndpoint(ServerRequest serverRequest) {
        UUID machineId = UUID.fromString(serverRequest.pathVariable(Constants.MACHINE_ID));
        Mono<Endpoint> endpointToSave = serverRequest.bodyToMono(Endpoint.class);

        log.debug("Received endpoint data: {}", endpointToSave);
        return endpointToSave
                .flatMap(endpoint -> ServerResponse.ok().body(endpointService.saveEndpoint(machineId, endpoint),
                        Endpoint.class));
    }

    public Mono<ServerResponse> getEndpoint(ServerRequest serverRequest) {
        UUID machineId = UUID.fromString(serverRequest.pathVariable(Constants.MACHINE_ID));
        log.debug("Retrieving endpoint data for machine ID: {}", machineId);

        Mono<Endpoint> endpointData = endpointService.getEndpoint(machineId);
        return endpointData.flatMap(endpoint -> ServerResponse.ok().bodyValue(endpoint))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getEndpoints(ServerRequest serverRequest) {
        log.debug("Retrieving all endpoint data");

        Flux<Endpoint> endpointsData = endpointService.getEndpoints();
        return ServerResponse.ok().body(endpointsData, Endpoint.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

}

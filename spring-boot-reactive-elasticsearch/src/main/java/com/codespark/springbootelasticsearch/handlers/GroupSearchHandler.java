package com.codespark.springbootelasticsearch.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.codespark.springbootelasticsearch.models.Criteria;
import com.codespark.springbootelasticsearch.models.Endpoint;
import com.codespark.springbootelasticsearch.models.SearchResult;
import com.codespark.springbootelasticsearch.services.GroupSearchService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GroupSearchHandler {

    private final GroupSearchService groupSearchService;

    public Mono<ServerResponse> groupEndpoints(ServerRequest serverRequest) {
        Mono<Criteria> groupCriteria = serverRequest.bodyToMono(Criteria.class);

        return groupCriteria.flatMap(
                criteria -> ServerResponse.ok().body(groupSearchService.groupEndpoints(criteria), Endpoint.class))
                .switchIfEmpty(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> searchEndpoints(ServerRequest serverRequest) {
        String query = serverRequest.queryParam("q")
                .orElseThrow(() -> new IllegalArgumentException("Query is required!"));

        return ServerResponse.ok().body(groupSearchService.searchEndpoints(query), SearchResult.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

}

package com.codespark.springbootelasticsearch.services;

import org.springframework.stereotype.Service;

import com.codespark.springbootelasticsearch.common.Mappings;
import com.codespark.springbootelasticsearch.data.EndpointSearchRepository;
import com.codespark.springbootelasticsearch.models.Criteria;
import com.codespark.springbootelasticsearch.models.Endpoint;
import com.codespark.springbootelasticsearch.models.SearchResult;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GroupSearchService {

    private final EndpointSearchRepository endpointSearchRepository;

    public Flux<Endpoint> groupEndpoints(Criteria criteria) {
        return endpointSearchRepository.groupEndpoints(criteria);
    }

    public Mono<SearchResult> searchEndpoints(String query) {
        return endpointSearchRepository.searchEndpoints(Mappings.searchableFieldNames, query);
    }

}

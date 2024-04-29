package com.codespark.springbootelasticsearch.data;

import java.util.UUID;

import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Repository;

import com.codespark.springbootelasticsearch.common.Constants;
import com.codespark.springbootelasticsearch.common.Queries;
import com.codespark.springbootelasticsearch.models.Endpoint;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class EndpointCustomRepository {

    private final ReactiveElasticsearchOperations elasticsearchOperations;

    public Mono<Endpoint> save(Endpoint entity) {
        return elasticsearchOperations.save(entity, IndexCoordinates.of(entity.getIndexName()));
    }

    public Mono<Endpoint> findById(UUID id) {
        String indexToSearch = Endpoint.builder().machineId(id).build().getIndexName();
        return elasticsearchOperations.get(id.toString(), Endpoint.class, IndexCoordinates.of(indexToSearch));
    }

    public Flux<Endpoint> findAll() {
        String indexToSearchByWildcard = Endpoint.INDEX_NAME_PREFIX + Constants.WILDCARD;
        return elasticsearchOperations
                .search(new StringQuery(Queries.SEARCH_ALL_BY_WILDCARD_QUERY_STRING), Endpoint.class,
                        IndexCoordinates.of(indexToSearchByWildcard))
                .map(SearchHit::getContent);
    }

}

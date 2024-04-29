package com.codespark.springbootelasticsearch.data;

import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Repository;

import com.codespark.springbootelasticsearch.common.Constants;
import com.codespark.springbootelasticsearch.common.utils.QueryMapper;
import com.codespark.springbootelasticsearch.common.utils.SearchQuery;
import com.codespark.springbootelasticsearch.models.Criteria;
import com.codespark.springbootelasticsearch.models.Endpoint;
import com.codespark.springbootelasticsearch.models.SearchResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EndpointSearchRepository {

    private final QueryMapper queryMapper;
    private final SearchQuery searchQuery;
    private final ReactiveElasticsearchOperations elasticsearchOperations;

    private static final String indexToSearchByWildcard = Endpoint.INDEX_NAME_PREFIX + Constants.WILDCARD;

    public Flux<Endpoint> groupEndpoints(Criteria criteria) {
        NativeQuery query = queryMapper.getQuery(criteria);

        log.info("Grouping endpoints based on {}", query.getQuery());
        return elasticsearchOperations.search(query, Endpoint.class, IndexCoordinates.of(indexToSearchByWildcard))
                .map(SearchHit::getContent);
    }

    public Mono<SearchResult> searchEndpoints(String[] fieldNames, String queryString) {
        NativeQuery query = searchQuery.buildQuery(fieldNames, queryString);
        return elasticsearchOperations
                .search(query, Endpoint.class, IndexCoordinates.of(indexToSearchByWildcard))
                .map(SearchHit::getContent).collectList()
                .map(results -> SearchResult.builder().endpoints(results).query(queryString).count(results.size())
                        .build());
    }

}

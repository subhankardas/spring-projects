package com.codespark.springbootelasticsearch.common.utils;

import java.util.Arrays;

import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.stereotype.Component;

import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery.Builder;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;

@Component
public class SearchQuery {

    public NativeQuery buildQuery(String[] fieldNames, String queryString) {
        // Create a multi match query with searchable fields.
        Builder multiMatchBuilder = QueryBuilders.multiMatch();
        multiMatchBuilder.fields(Arrays.asList(fieldNames)).query(queryString).type(TextQueryType.BoolPrefix);

        // Build the query with the multi match query.
        return NativeQuery.builder().withQuery(multiMatchBuilder.build()._toQuery()).build();
    }

}

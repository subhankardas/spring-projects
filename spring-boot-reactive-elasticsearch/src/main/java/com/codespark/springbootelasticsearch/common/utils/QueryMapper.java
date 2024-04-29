package com.codespark.springbootelasticsearch.common.utils;

import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.stereotype.Component;

import com.codespark.springbootelasticsearch.common.Mappings;
import com.codespark.springbootelasticsearch.models.Criteria;
import com.codespark.springbootelasticsearch.models.CriteriaBlock;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery.Builder;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;

@Component
public class QueryMapper {

    public NativeQuery getQuery(Criteria criteria) {
        // Read list of criteria blocks and compile the criteria into bool query.
        Builder boolBuilder = QueryBuilders.bool();
        for (CriteriaBlock cb : criteria.getCriteria()) {
            switch (cb.getOperator().toUpperCase()) {
                case "AND":
                    boolBuilder.must(
                            s -> s.match(k -> k.field(Mappings.fieldMapping.get(cb.getField())).query(cb.getValue())));
                    break;
                case "OR":
                    boolBuilder
                            .should(s -> s.match(
                                    k -> k.field(Mappings.fieldMapping.get(cb.getField())).query(cb.getValue())));
                    break;
                case "NOT":
                    boolBuilder.mustNot(
                            s -> s.match(k -> k.field(Mappings.fieldMapping.get(cb.getField())).query(cb.getValue())));
                    break;
                default:
                    break;
            }
        }

        // Build the query with the bool query.
        return NativeQuery.builder().withQuery(boolBuilder.build()._toQuery()).build();
    }

}

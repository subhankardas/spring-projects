package com.codespark.springbootelasticsearch.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResult {

    private String query;
    private List<Endpoint> endpoints;
    private int count;

}

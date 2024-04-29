package com.codespark.springbootelasticsearch.data;

import java.util.UUID;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

import com.codespark.springbootelasticsearch.models.Endpoint;

public interface EndpointRepository extends ReactiveElasticsearchRepository<Endpoint, UUID> {

}

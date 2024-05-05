package com.codespark.springbootkubernetes.data;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.codespark.springbootkubernetes.models.Movie;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieRepository extends R2dbcRepository<Movie, Integer> {

    Mono<Movie> findByName(String name);

    @Query("SELECT * FROM movies WHERE year >= :year")
    Flux<Movie> findAllReleasedAfter(int year);

}

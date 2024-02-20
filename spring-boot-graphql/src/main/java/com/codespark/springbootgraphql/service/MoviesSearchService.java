package com.codespark.springbootgraphql.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codespark.springbootgraphql.Movie;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MoviesSearchService {

    private RestTemplate restTemplate;

    private static final String API_KEY = "4289a13f";
    private static final String MOVIE_SEARCH_URL = "https://www.omdbapi.com/?apikey=%s&t=%s";

    @Async
    public CompletableFuture<Movie> getMovieBy(String name) {
        String queryMovieByName = String.format(MOVIE_SEARCH_URL, API_KEY,
                URLEncoder.encode(name, StandardCharsets.UTF_8));
        return CompletableFuture.completedFuture(restTemplate.getForObject(queryMovieByName, Movie.class));
    }

}

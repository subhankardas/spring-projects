package com.codespark.springbootgraphql.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.codespark.springbootgraphql.Audience;
import com.codespark.springbootgraphql.Movie;
import com.codespark.springbootgraphql.service.MoviesSearchService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MoviesController {

    private MoviesSearchService moviesSearchService;

    @QueryMapping // Maps to getMovieBy query
    public Movie getMovieBy(@Argument String title) throws InterruptedException, ExecutionException {
        return moviesSearchService.getMovieBy(title).get();
    }

    @SchemaMapping(typeName = "Movie", field = "audience") // Maps to audience field for movie type
    public Audience getAudienceFor(Movie movie) {
        return Audience.getByRating(movie.rating());
    }

}

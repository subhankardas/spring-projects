package com.codespark.springboottesting.services.interfaces;

import java.util.List;

import com.codespark.springboottesting.entities.Movie;

public interface MovieService {

    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(String genre);

    List<Movie> findByTitleContaining(String keyword);

}
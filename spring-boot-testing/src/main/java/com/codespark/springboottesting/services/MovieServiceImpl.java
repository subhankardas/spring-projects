package com.codespark.springboottesting.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codespark.springboottesting.entities.Movie;
import com.codespark.springboottesting.repositories.MovieRepository;
import com.codespark.springboottesting.services.interfaces.MovieService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public List<Movie> findByTitleContaining(String keyword) {
        return movieRepository.findByTitleContaining(keyword);
    }

}

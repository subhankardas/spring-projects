package com.codespark.springboottesting.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codespark.springboottesting.entities.Movie;
import com.codespark.springboottesting.repositories.MovieRepository;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTests {

    // Create mock dependencies //
    @Mock
    private MovieRepository movieRepository;

    // Create instance with injected mocks //
    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    void testFindByTitleContaining() {
        // Given
        List<Movie> results = List.of(new Movie(0l, "Avengers", "Action", 2019),
                new Movie(0l, "Avengers: Endgame", "Action", 2021));
        when(movieRepository.findByTitleContaining("Avenge")).thenReturn(results);

        // When
        List<Movie> actual = movieService.findByTitleContaining("Avenge");

        // Then
        assertEquals(results, actual);
        for (int i = 0; i < results.size(); i++) {
            assertEquals(results.get(i), actual.get(i));
        }
    }

}

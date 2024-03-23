package com.codespark.springboottesting.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.codespark.springboottesting.entities.Movie;
import com.codespark.springboottesting.repositories.MovieRepository;
import com.codespark.springboottesting.services.interfaces.MovieService;

@WebMvcTest(MovieSearchController.class)
@AutoConfigureMockMvc
public class MovieSearchControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    void testSearch_OK() throws Exception {
        // Given
        when(movieService.findByTitleContaining("Avenge")).thenReturn(List.of(
                new Movie(0l, "Avengers", "Action", 2019),
                new Movie(0l, "Avengers: Endgame", "Action", 2021)));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/search?q=Avenge"))

                // Then
                .andExpect(MockMvcResultMatchers.status().isOk()) // 200 OK

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Avengers")) // 1st movie
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].releaseYear").value(2019))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Avengers: Endgame")) // 2nd movie
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].releaseYear").value(2021));
    }

    @Test
    void testSearch_NotFound() throws Exception {
        // Given
        when(movieService.findByTitleContaining("ABCD")).thenReturn(List.of(
                new Movie(0l, "Mission Impossible", "Action", 2019),
                new Movie(0l, "Sherlock Holmes", "Action", 2021)));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/search?q=ABC"))

                // Then
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}

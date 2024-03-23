package com.codespark.springboottesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.codespark.springboottesting.entities.Movie;
import com.codespark.springboottesting.services.MovieServiceImpl;
import com.codespark.springboottesting.services.interfaces.MovieService;

@SpringBootTest
class SpringBootTestingApplicationTests {

    @Test
    void simpleMockitoDemo() {
        // 1. Create mock object of MovieService using mockito
        MovieService mockMovieService = mock(MovieServiceImpl.class);

        // 2. Stubbing mock behavior
        when(mockMovieService.findByGenre("Drama"))
                .thenReturn(List.of(new Movie(1l, "Notebook", "Drama", 2019)));

        // Call service methods
        List<Movie> actual = mockMovieService.findByGenre("Drama");
        assertEquals(List.of(new Movie(1l, "Notebook", "Drama", 2019)), actual);
    }

}

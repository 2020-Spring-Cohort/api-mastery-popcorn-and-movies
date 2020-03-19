package org.wcci.apimastery;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MovieControllerTest {
    private MovieRepository movieRepository;
    private GenreRepository genreRepository;


    @Test
    public void retreivedMovieReturnsAListOfMoviesFromMockRepo() {
        movieRepository = mock(MovieRepository.class);
        Genre testGenre = new Genre("comedy");
        MovieController underTest = new MovieController(movieRepository);
        Movie testMovie = new Movie("The Hangover", testGenre);
        when(movieRepository.findAll()).thenReturn(Collections.singletonList(testMovie));
        Collection<Movie> result = underTest.retrievedMovies();
        verify(movieRepository).findAll();
    }
    @Test
    public void retrievedMovieReturnsAListOfMoviesContainingMockMovies() {
        movieRepository = mock(MovieRepository.class);
        MovieController underTest = new MovieController(movieRepository);
        Genre testGenre = new Genre("comedy");
        Movie testMovie = new Movie("The Hangover", testGenre);
        when(movieRepository.findAll()).thenReturn(Collections.singletonList(testMovie));
        Collection<Movie> result = underTest.retrievedMovies();
        verify(movieRepository).findAll();
        assertThat(result).contains(testMovie);
    }
    @Test
    public void underTestIsWiredCorrectlyWithAnnotation() throws Exception {
        movieRepository = mock(MovieRepository.class);
        MovieController underTest = new MovieController(movieRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/movies")).andExpect(status().isOk());
    }
}

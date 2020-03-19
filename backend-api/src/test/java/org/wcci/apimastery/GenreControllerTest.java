package org.wcci.apimastery;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.Collections;



import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GenreControllerTest {


    private GenreRepository genreRepository;


    @Test
    public void retreivedGenreReturnsAListOfGenresFromMockRepo() {
        genreRepository = mock(GenreRepository.class);
        GenreController underTest = new GenreController(genreRepository);
        Genre testGenre = new Genre("comedy");
        when(genreRepository.findAll()).thenReturn(Collections.singletonList(testGenre));
        Collection<Genre> result = underTest.retrievedGenres();
        verify(genreRepository).findAll();


    }

    @Test
    public void retrievedGenreReturnsAListOfGenresContainingMockGenres() {
        genreRepository = mock(GenreRepository.class);
        GenreController underTest = new GenreController(genreRepository);
        Genre testGenre = new Genre("comedy");
        when(genreRepository.findAll()).thenReturn(Collections.singletonList(testGenre));
        Collection<Genre> result = underTest.retrievedGenres();
        verify(genreRepository).findAll();
        assertThat(result).contains(testGenre);

    }
    @Test
    public void underTestIsWiredCorrectlyWithAnnotation() throws Exception {
        genreRepository = mock(GenreRepository.class);
        GenreController underTest = new GenreController(genreRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/genres")).andExpect(status().isOk());
    }
}
package org.wcci.apimastery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.apimastery.MovieRepository;
import org.wcci.apimastery.Movie;
import org.wcci.apimastery.GenreRepository;
import org.wcci.apimastery.ActorRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {
    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    private GenreRepository genreRepo;
    @Autowired
    private ActorRepository actorRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void moviesShouldHaveGenres() {
        Genre testGenre = new Genre("comedy");
        genreRepo.save(testGenre);

        Movie testMovie = new Movie("Hangover", testGenre);
        Movie testMovie2 = new Movie("Hangover 2",testGenre);
        movieRepo.save(testMovie);
        movieRepo.save(testMovie2);

        entityManager.flush();
        entityManager.clear();

        Genre retrievedGenre = genreRepo.findById(testGenre.getId()).get();
        Movie retrievedMovie = movieRepo.findById(testMovie.getId()).get();
        Movie retrievedMovie2 = movieRepo.findById(testMovie2.getId()).get();
        assertThat(retrievedGenre.getMovies()).contains(retrievedMovie, retrievedMovie2);

    }
}

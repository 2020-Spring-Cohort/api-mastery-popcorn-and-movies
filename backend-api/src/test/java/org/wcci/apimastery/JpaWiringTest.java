package org.wcci.apimastery;

        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
        import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

        import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {
    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    private GenreRepository genreRepo;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ActorRepository actorRepo;


    @Test
    public void moviesShouldHaveAGenre() {
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
    @Test
    public void moviesShouldHaveMultipleActors() {
        Actor testActor1 = new Actor("Bradley");
        Actor testActor2 = new Actor("Zack");
        Genre testGenre = new Genre("comedy");
        Movie testMovie1 = new Movie("Hangover",testGenre, testActor1, testActor2);
        Movie testMovie2 = new Movie("Hangover 2",testGenre,testActor2);
        Movie testMovie3 = new Movie("Hangover 3",testGenre,testActor1);
        actorRepo.save(testActor1);
        actorRepo.save(testActor2);
        genreRepo.save(testGenre);
        movieRepo.save(testMovie1);
        movieRepo.save(testMovie2);
        movieRepo.save(testMovie3);

        entityManager.flush();
        entityManager.clear();

        Movie retrievedMovie = movieRepo.findById(testMovie1.getId()).get();
        Actor retrievedActor1 = actorRepo.findById(testActor1.getId()).get();
        Actor retrievedActor2 = actorRepo.findById(testActor2.getId()).get();
        assertThat(retrievedMovie.getActors()).contains(testActor1, testActor2);
        assertThat(retrievedActor1.getMovies()).contains(testMovie1, testMovie3);
        assertThat(retrievedActor2.getMovies()).contains(testMovie1, testMovie2);

    }
    @Test
    public void displaySingleMovieData() {
        Genre testGenre = new Genre("comedy");
        Actor testActor = new Actor("Bradley");
        Movie testMovie = new Movie("Hangover",testGenre, testActor);
        actorRepo.save(testActor);
        genreRepo.save(testGenre);
        movieRepo.save(testMovie);
        testMovie.setDate("06/02/2009");
        testMovie.setDirectors("Todd Phillips");

        assertThat(testMovie.getDate()).isEqualTo("06/02/2009");
        assertThat(testMovie.getDirectors()).isEqualTo("Todd Phillips");

    }
}

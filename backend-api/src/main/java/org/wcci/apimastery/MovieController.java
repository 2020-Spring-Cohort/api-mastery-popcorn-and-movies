package org.wcci.apimastery;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MovieController {
    private MovieRepository movieRepository;
    private ActorRepository actorRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public Collection<Movie> retrievedMovies() {
        return (Collection<Movie>) movieRepository.findAll();
    }
    @GetMapping("/movies/{id}")
    public Movie retrievedMovies(@PathVariable Long id) {
        return movieRepository.findById(id).get();
    }
    //Add  actor to Movie
    @PatchMapping("/movies/{id}")
    public Movie updateMovieWithActors(@PathVariable Long id, @RequestBody Actor requestBodyActor) {
        Movie movieToPatch = movieRepository.findById(id).get();
        Actor actorToAdd = new Actor(requestBodyActor.getName());
        actorRepository.save(actorToAdd);
        return movieRepository.save(movieToPatch);
    }
}

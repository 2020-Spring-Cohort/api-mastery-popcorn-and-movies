package org.wcci.apimastery;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private GenreRepository genreRepository;
    private MovieRepository movieRepository;
    private ActorRepository actorRepository;


    public GenreController(GenreRepository genreRepository, MovieRepository movieRepository, ActorRepository actorRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;

    }

    @GetMapping("")
    public Collection<Genre> retrievedGenres() {
        return (Collection<Genre>) genreRepository.findAll();
    }

    @GetMapping("/{id}")
    public Genre displaySingleGenre(@PathVariable long id) {
        return genreRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        Genre genreToRemove = genreRepository.findById(id).get();

        for (Movie movieToRemove : genreToRemove.getMovies()) {
            for (Actor actorToRemove : movieToRemove.getActors()) {
                actorRepository.delete(actorToRemove);
            }
            movieRepository.delete(movieToRemove);
        }
        genreRepository.deleteById(id);
    }
    @PostMapping("")
    public Genre createGenre(@RequestBody Genre genreToAdd) {
        return genreRepository.save(genreToAdd);
    }
    @PatchMapping("/{id}/movies")
    public Genre updateGenre(@PathVariable Long id, @RequestBody Movie movieToPatch) {
        Genre genreToUpdate = genreRepository.findById(id).get();
        Movie movieToAdd = new Movie(movieToPatch.getTitle(), genreToUpdate, (Actor) movieToPatch.getActors());
        movieRepository.save(movieToAdd);
        return genreRepository.save(genreToUpdate);
    }
}
package org.wcci.apimastery;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
@RestController
public class GenreController {

    private GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;

    }
    @RequestMapping("/genres")
    public Collection<Genre> retrievedGenres() {
        return (Collection<Genre>) genreRepository.findAll();

    }

}
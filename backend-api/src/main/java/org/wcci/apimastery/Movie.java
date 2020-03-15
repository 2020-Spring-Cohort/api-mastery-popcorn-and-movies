package org.wcci.apimastery;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import java.util.*;

import static java.util.Arrays.asList;

@Entity
public class Movie {

    private String title;
//    private String directors;
//    private Long date;
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Genre genre;


    @ManyToMany
    private Collection<Actor> actors;

    protected Movie() {
    }

    public Movie(String title, Genre genre, Actor... actors) {
        this.title = title;
        this.genre = genre;
        this.actors = new ArrayList<>(Arrays.asList(actors));
    }

    public Collection<Actor> getActors() {
        return actors;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

//    public String getDirectors() {
//        return directors;
//    }
//
//    public Long getDate() {
//        return date;
//    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", genre=" + genre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getTitle().equals(movie.getTitle()) &&
                getId().equals(movie.getId()) &&
                getGenre().equals(movie.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getId(), getGenre());
    }
}
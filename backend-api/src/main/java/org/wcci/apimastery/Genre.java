package org.wcci.apimastery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "genre")
    private Collection<Movie> movies;
    private String genreType;

    public Genre() {}
    public Genre(String genreType) {
        this.genreType = genreType;
    }

    public String getGenreType() {
        return genreType;
    }

    public Long getId() {
        return id;
    }

    public Collection<Movie> getMovies() {
        return movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return Objects.equals(getId(), genre.getId()) &&
                Objects.equals(getGenreType(), genre.getGenreType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGenreType());
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genreType='" + genreType + '\'' +
                '}';
    }
}

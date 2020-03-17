package org.wcci.apimastery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Actor {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany(mappedBy = "actors")
    private Collection<Movie>movies;

    private String name;
    private int age;

    public Actor(){}
    public Actor(String name) {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public int getAge() {
        return age;
    }
    public Long getId() {
    return id;
}

    public Collection<Movie>getMovies(){
        return movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return getId().equals(actor.getId()) &&
                getName().equals(actor.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}

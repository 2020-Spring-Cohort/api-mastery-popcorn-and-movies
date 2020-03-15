package org.wcci.apimastery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Actor {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int age;

    public Actor(){}
    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return getAge() == actor.getAge() &&
                Objects.equals(id, actor.id) &&
                Objects.equals(getName(), actor.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getAge());
    }
}

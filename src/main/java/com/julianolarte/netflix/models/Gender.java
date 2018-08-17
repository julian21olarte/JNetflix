package com.julianolarte.netflix.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Gender {
    private int id;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gender gender = (Gender) o;
        return id == gender.id &&
                Objects.equals(description, gender.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, description);
    }
}

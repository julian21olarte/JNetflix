package com.julianolarte.netflix.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favorite_movie", schema = "jnetflix")
public class FavoriteMovie {
    private int id;
    private Profile profile;
    private Movie movie;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteMovie that = (FavoriteMovie) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "profile", referencedColumnName = "id", nullable = false)
    @JsonProperty(value = "profile")
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profileByProfile) {
        this.profile = profileByProfile;
    }

    @ManyToOne
    @JoinColumn(name = "movie", referencedColumnName = "id", nullable = false)
    @JsonProperty(value = "movie")
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movieByMovie) {
        this.movie = movieByMovie;
    }
}

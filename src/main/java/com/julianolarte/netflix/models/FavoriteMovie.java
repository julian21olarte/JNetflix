package com.julianolarte.netflix.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favorite_movie", schema = "jnetflix")
public class FavoriteMovie {
    private int id;
    private Profile profileByProfile;
    private Movie movieByMovie;

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
    public Profile getProfileByProfile() {
        return profileByProfile;
    }

    public void setProfileByProfile(Profile profileByProfile) {
        this.profileByProfile = profileByProfile;
    }

    @ManyToOne
    @JoinColumn(name = "movie", referencedColumnName = "id", nullable = false)
    public Movie getMovieByMovie() {
        return movieByMovie;
    }

    public void setMovieByMovie(Movie movieByMovie) {
        this.movieByMovie = movieByMovie;
    }
}

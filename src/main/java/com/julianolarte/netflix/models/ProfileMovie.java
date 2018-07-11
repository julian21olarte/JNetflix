package com.julianolarte.netflix.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "profile_movie", schema = "jnetflix")
public class ProfileMovie {
    private int id;
    private Date watchDate;
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

    @Basic
    @Column(name = "watch_date")
    public Date getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(Date watchDate) {
        this.watchDate = watchDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileMovie that = (ProfileMovie) o;
        return id == that.id &&
                Objects.equals(watchDate, that.watchDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, watchDate);
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

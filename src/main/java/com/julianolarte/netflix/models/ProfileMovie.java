package com.julianolarte.netflix.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "profile_movie", schema = "jnetflix")
public class ProfileMovie {
    private int id;
    private Date watchDate;
    private Profile profile;
    private Movie movie;

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
    @JsonBackReference(value = "profile")
    @JoinColumn(name = "profile", referencedColumnName = "id", nullable = false)
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profileByProfile) {
        this.profile = profileByProfile;
    }

    @ManyToOne
    @JsonBackReference(value = "movie")
    @JoinColumn(name = "movie", referencedColumnName = "id", nullable = false)
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movieByMovie) {
        this.movie = movieByMovie;
    }
}

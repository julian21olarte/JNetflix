package com.julianolarte.netflix.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Movie {
    private int id;
    private String name;
    private String description;
    private String language;
    private String year;
    private int durationMin;
    private String movieId;
    private Collection<FavoriteMovie> favoriteMovies;
    private Gender gender;
    private Collection<ProfileMovie> profileMovies;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "movie_id")
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    @Basic
    @Column(name = "duration_min")
    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                durationMin == movie.durationMin &&
                Objects.equals(name, movie.name) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(language, movie.language) &&
                Objects.equals(year, movie.year) &&
                Objects.equals(movieId, movie.movieId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, language, year, durationMin, movieId);
    }

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    public Collection<FavoriteMovie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(Collection<FavoriteMovie> favoriteMoviesById) {
        this.favoriteMovies = favoriteMoviesById;
    }

    @ManyToOne
    @JoinColumn(name = "gender", referencedColumnName = "id", nullable = false)
    @JsonProperty(value = "gender")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender genderByGender) {
        this.gender = genderByGender;
    }

    @OneToMany(mappedBy = "movie")
    @JsonManagedReference(value = "movie")
    @JsonIgnore
    public Collection<ProfileMovie> getProfileMovies() {
        return profileMovies;
    }

    public void setProfileMovies(Collection<ProfileMovie> profileMoviesById) {
        this.profileMovies = profileMoviesById;
    }
}

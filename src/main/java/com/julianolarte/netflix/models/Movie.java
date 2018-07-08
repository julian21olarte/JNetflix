package com.julianolarte.netflix.models;

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
    private Collection<FavoriteMovie> favoriteMoviesById;
    private Gender genderByGender;
    private Collection<ProfileMovie> profileMoviesById;

    @Id
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
                Objects.equals(year, movie.year);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, language, year, durationMin);
    }

    @OneToMany(mappedBy = "movieByMovie")
    public Collection<FavoriteMovie> getFavoriteMoviesById() {
        return favoriteMoviesById;
    }

    public void setFavoriteMoviesById(Collection<FavoriteMovie> favoriteMoviesById) {
        this.favoriteMoviesById = favoriteMoviesById;
    }

    @ManyToOne
    @JoinColumn(name = "gender", referencedColumnName = "id", nullable = false)
    public Gender getGenderByGender() {
        return genderByGender;
    }

    public void setGenderByGender(Gender genderByGender) {
        this.genderByGender = genderByGender;
    }

    @OneToMany(mappedBy = "movieByMovie")
    public Collection<ProfileMovie> getProfileMoviesById() {
        return profileMoviesById;
    }

    public void setProfileMoviesById(Collection<ProfileMovie> profileMoviesById) {
        this.profileMoviesById = profileMoviesById;
    }
}

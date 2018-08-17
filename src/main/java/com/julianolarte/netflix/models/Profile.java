package com.julianolarte.netflix.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Profile {
    private int id;
    private String name;
    private User user;
    private Collection<FavoriteMovie> favoriteMovies;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id == profile.id &&
                Objects.equals(name, profile.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User userByUser) {
        this.user = userByUser;
    }

    @OneToMany(mappedBy = "profile")
    @JsonIgnore
    public Collection<FavoriteMovie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(Collection<FavoriteMovie> favoriteMoviesById) {
        this.favoriteMovies = favoriteMoviesById;
    }

    @OneToMany(mappedBy = "profile")
    @JsonManagedReference(value = "profile")
    @JsonIgnore
    public Collection<ProfileMovie> getProfileMovies() {
        return profileMovies;
    }

    public void setProfileMovies(Collection<ProfileMovie> profileMoviesById) {
        this.profileMovies = profileMoviesById;
    }
}

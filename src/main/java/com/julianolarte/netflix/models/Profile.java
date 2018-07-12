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
    private User userByUser;
    private Collection<FavoriteMovie> favoriteMoviesById;
    private Collection<ProfileMovie> profileMoviesById;

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
    @JsonBackReference(value = "userByUser")
    public User getUserByUser() {
        return userByUser;
    }

    public void setUserByUser(User userByUser) {
        this.userByUser = userByUser;
    }

    @OneToMany(mappedBy = "profileByProfile")
    public Collection<FavoriteMovie> getFavoriteMoviesById() {
        return favoriteMoviesById;
    }

    public void setFavoriteMoviesById(Collection<FavoriteMovie> favoriteMoviesById) {
        this.favoriteMoviesById = favoriteMoviesById;
    }

    @OneToMany(mappedBy = "profileByProfile")
    @JsonManagedReference(value = "profileByProfile")
    @JsonIgnore
    public Collection<ProfileMovie> getProfileMoviesById() {
        return profileMoviesById;
    }

    public void setProfileMoviesById(Collection<ProfileMovie> profileMoviesById) {
        this.profileMoviesById = profileMoviesById;
    }
}

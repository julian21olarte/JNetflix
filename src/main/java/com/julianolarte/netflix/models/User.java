package com.julianolarte.netflix.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    private int id;
    private String email;
    private String password;
    private Date birthdate;
    private Collection<Profile> profiles;

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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "birthdate")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(birthdate, user.birthdate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, password, birthdate);
    }

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user")
    @JsonProperty(value = "profiles")
    public Collection<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Collection<Profile> profilesById) {
        this.profiles = profilesById;
    }
}

package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.FavoriteMovie;
import com.julianolarte.netflix.models.Movie;
import com.julianolarte.netflix.models.Profile;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface FavoriteMovieRepository extends CrudRepository<FavoriteMovie, Long> {
    boolean existsByMovieAndProfile(Movie movieByMovie, Profile profileByProfile);

    @Transactional
    void deleteByProfileAndMovie(Profile profile, Movie movie);
}

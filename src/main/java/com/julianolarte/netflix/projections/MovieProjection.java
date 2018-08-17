package com.julianolarte.netflix.projections;

import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.models.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(
    name = "movie",
    types = { Movie.class })
public interface MovieProjection {

    @Value("#{target.Movie.id}")
    Long getId();

    @Value("#{target.Movie.name}")
    String getName();

    @Value("#{target.Movie.description}")
    String getDescription();

    @Value("#{target.Movie.language}")
    String getLanguage();

    @Value("#{target.Movie.year}")
    String getYear();

    @Value("#{target.Movie.movieId}")
    String getMovieId();

    @Value("#{target.Movie.durationMin}")
    Long getDurationMin();

    @Value("#{target.Movie.gender}")
    Gender getGender();

    @Value("#{target.favorite}")
    boolean getFavorite();
}

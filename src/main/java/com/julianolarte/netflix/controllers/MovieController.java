package com.julianolarte.netflix.controllers;

import com.julianolarte.netflix.models.FavoriteMovie;
import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.models.Movie;
import com.julianolarte.netflix.models.Profile;
import com.julianolarte.netflix.projections.FavoriteMovieProjection;
import com.julianolarte.netflix.repositories.FavoriteMovieRepository;
import com.julianolarte.netflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private FavoriteMovieRepository favoriteMovieRepository;


    @RequestMapping(value = "/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> getMovies() {
        return this.movieRepository.findAll();
    }

    @RequestMapping(value = "/movieWithFavorite", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<FavoriteMovieProjection> getMoviesWithFavorite(@RequestParam("profile") int profileId) {
        Profile profile = new Profile();
        profile.setId(profileId);
        return this.movieRepository.findAllWithFavoriteByProfile(profile);
    }

    @RequestMapping(value = "/movieLast10", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> getLast10Movies() {
        return this.movieRepository.findTop10ByOrderByYearDesc();
    }


    @RequestMapping(value = "/movieByYear", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> getByYear(@RequestParam("year") String year) {
        return this.movieRepository.findByYearOrderByName(year);
    }

    @RequestMapping(value = "/movieByGender", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> getByGender(@RequestParam("gender") int genderId) {
        Gender gender = new Gender();
        gender.setId(genderId);
        return this.movieRepository.findByGenderByGender(gender);
    }

    @RequestMapping(value = "/movieByProfile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> getByProfile(@RequestParam("profile") int profileId) {
        Profile profile = new Profile();
        profile.setId(profileId);

        return this.movieRepository.findByProfileMoviesById_ProfileByProfile(profile);
    }


    @RequestMapping(value = "/addFavoriteMovie", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addFavoriteMovie(@RequestBody FavoriteMovie favoriteMovie) {

        Map<String, String> responseMap = new HashMap<>();
        if(this.favoriteMovieRepository.existsByMovieByMovieAndProfileByProfile(favoriteMovie.getMovieByMovie(), favoriteMovie.getProfileByProfile())) {
            this.favoriteMovieRepository
                .deleteByProfileByProfileAndMovieByMovie(
                    favoriteMovie.getProfileByProfile(),
                    favoriteMovie.getMovieByMovie());
            responseMap.put("method", "DELETE");
        }
        else {
            this.favoriteMovieRepository.save(favoriteMovie);
            responseMap.put("method", "SAVE");
        }
        return new ResponseEntity(responseMap, HttpStatus.OK);
    }
}

package com.julianolarte.netflix.controllers;

import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.models.Movie;
import com.julianolarte.netflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(value = "/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> getMovies() {
        return this.movieRepository.findAll();
    }

    @RequestMapping(value = "/movieLast10", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> getLast10Movies() {
        return this.movieRepository.findTop10ByOrderByYearDesc();
    }


    @RequestMapping(value = "/movieByYear", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> getByYear(@RequestParam("year") String year) {
        System.out.println(year);
        return this.movieRepository.findByYearOrderByName(year);
    }

    @RequestMapping(value = "/movieByGender", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Movie> getByGender(@RequestParam("gender") int genderId) {
        Gender gender = new Gender();
        gender.setId(genderId);
        return this.movieRepository.findByGenderByGender(gender);
    }
}

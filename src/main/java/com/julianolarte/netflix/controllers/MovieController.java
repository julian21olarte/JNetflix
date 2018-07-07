package com.julianolarte.netflix.controllers;

import com.julianolarte.netflix.models.Pelicula;
import com.julianolarte.netflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(value = "/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Pelicula> getMovies() {
        return this.movieRepository.findAll();
    }

    @RequestMapping(value = "/movieLast10", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Pelicula> getLast10Movies() {
        return this.movieRepository.findTop10ByOrderByAnoDesc();
    }


    @RequestMapping(value = "movieByAño", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Pelicula> getByYear(@RequestBody String year) {
        return this.movieRepository.findByAnoOrderByNombre(year);
    }
}

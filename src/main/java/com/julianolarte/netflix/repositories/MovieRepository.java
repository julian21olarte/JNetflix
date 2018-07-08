package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    /**
     * @return Last 10 movies order by year Desc
     */
    Iterable<Movie> findTop10ByOrderByYearDesc();


    /**
     * @param year
     * @return movies by year order by name
     */
    Iterable<Movie> findByYearOrderByName(String year);


    /**
     * @param name
     * @return movies by name
     */
    Iterable<Movie> findByName(String name);
}

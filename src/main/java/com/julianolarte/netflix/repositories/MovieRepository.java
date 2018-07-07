package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.Pelicula;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Pelicula, Long> {

    /**
     * @return Last 10 movies order by year Desc
     */
    Iterable<Pelicula> findTop10ByOrderByAnoDesc();


    /**
     * @param ano
     * @return movies oby year order by name
     */
    Iterable<Pelicula> findByAnoOrderByNombre(String ano);


    /**
     * @param nombre
     * @return movies by name
     */
    Iterable<Pelicula> findByNombre(String nombre);
}

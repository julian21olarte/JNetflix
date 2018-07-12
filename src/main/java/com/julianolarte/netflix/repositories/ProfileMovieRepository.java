package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.ProfileMovie;
import org.springframework.data.repository.CrudRepository;

public interface ProfileMovieRepository extends CrudRepository<ProfileMovie, Long> {
}

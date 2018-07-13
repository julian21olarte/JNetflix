package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.models.Movie;
import com.julianolarte.netflix.models.Profile;
import com.julianolarte.netflix.projections.FavoriteMovieProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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


    Iterable<Movie> findByGenderByGender(Gender gender);


    /**
     * @param name
     * @return movies by name
     */
    Iterable<Movie> findByName(String name);


    /**
     * @param profile
     * @return movies by profile finded in profile_movie table
     */
    Iterable<Movie> findByProfileMoviesById_ProfileByProfile(Profile profile);


    @Query(
        "select m.id as id, " +
            "m.description as description, " +
            "m.durationMin as durationMin, " +
            "m.language as language, " +
            "m.name as name, " +
            "m.year as year, " +
            "m.genderByGender as gender, " +
            "case when(fm.id is null) then false " +
            "   else true end as favorite " +
        "from Movie m left join FavoriteMovie fm " +
            "on m.id = fm.movieByMovie.id " +
            "and fm.profileByProfile.id = :#{#profile.id}")
    Iterable<FavoriteMovieProjection> findAllWithFavoriteByProfile(@Param("profile") Profile profile);
}

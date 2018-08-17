package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.models.Movie;
import com.julianolarte.netflix.models.Profile;
import com.julianolarte.netflix.projections.MovieProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

    /**
     * @return Last 10 movies order by year Desc
     */
    Iterable<Movie> findTop10ByOrderByYearDesc();


    /**
     * @param year
     * @return movies by year order by name
     */
    Iterable<Movie> findByYearOrderByName(String year);


    @Query(
        "select m as Movie, " +
            "case when(fm.id is null) then false " +
            "else true end as favorite " +
        "from Movie m left join FavoriteMovie fm " +
            "on m.id = fm.movie.id " +
            "and fm.profile = :#{#profile} " +
        "where m.gender = :#{#gender}")
    Iterable<MovieProjection> findByGender(@Param("gender") Gender gender, @Param("profile") Profile profile);


    /**
     * @param name
     * @return movies by name
     */
    Iterable<Movie> findByName(String name);


    /**
     * @param profile
     * @return movies by profile finded in profile_movie table
     */
    @Query(
        "select m as Movie, " +
            "case when(fm.id is null) then false " +
            "else true end as favorite " +
        "from Movie m inner join ProfileMovie pm " +
            "on m = pm.movie " +
            "and pm.profile = :#{#profile} " +
        "left join FavoriteMovie fm " +
            "on m = fm.movie " +
            "and fm.profile = :#{#profile}")
    Iterable<MovieProjection> findByProfile(@Param("profile") Profile profile);


    @Query(
        "select m as Movie, " +
            "case when(fm.id is null) then false " +
            "else true end as favorite " +
        "from Movie m left join FavoriteMovie fm " +
            "on m = fm.movie " +
            "and fm.profile = :#{#profile}")
    Iterable<MovieProjection> findAllWithFavoriteByProfile(@Param("profile") Profile profile);
}

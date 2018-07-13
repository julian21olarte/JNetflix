package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.models.Movie;
import com.julianolarte.netflix.models.Profile;
import com.julianolarte.netflix.projections.MovieProjection;
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


    @Query(
        "select m as Movie, " +
            "case when(fm.id is null) then false " +
            "else true end as favorite " +
        "from Movie m left join FavoriteMovie fm " +
            "on m.id = fm.movieByMovie.id " +
            "and fm.profileByProfile = :#{#profile} " +
        "where m.genderByGender = :#{#gender}")
    Iterable<MovieProjection> findByGenderByGender(@Param("gender") Gender gender, @Param("profile") Profile profile);


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
            "on m = pm.movieByMovie " +
            "and pm.profileByProfile = :#{#profile} " +
        "left join FavoriteMovie fm " +
            "on m = fm.movieByMovie " +
            "and fm.profileByProfile = :#{#profile}")
    Iterable<MovieProjection> findByProfileMoviesById_ProfileByProfile(@Param("profile") Profile profile);


    @Query(
        "select m as Movie, " +
            "case when(fm.id is null) then false " +
            "else true end as favorite " +
        "from Movie m left join FavoriteMovie fm " +
            "on m = fm.movieByMovie " +
            "and fm.profileByProfile = :#{#profile}")
    Iterable<MovieProjection> findAllWithFavoriteByProfile(@Param("profile") Profile profile);
}

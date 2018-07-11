package com.julianolarte.netflix.repositoryTest;

import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.models.Movie;
import com.julianolarte.netflix.repositories.GenderRepository;
import com.julianolarte.netflix.repositories.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenderRepository genderRepository;


    @Before
    @Rollback(true)
    public void before() {

        //save one gender
        Gender gender = new Gender();
        gender.setDescription("gender for test in movies repository");
        this.genderRepository.save(gender);

        // save 10 movies for test
        List<Movie> movieList = new ArrayList<>();
        Movie movie;
        for(int i = 0; i < 10; i++) {
            movie = new Movie();
            movie.setName("movie #" + i);
            movie.setDescription("Description Movie #" + i);
            movie.setDurationMin(120);
            movie.setLanguage("English");
            movie.setGenderByGender(gender);
            movie.setYear("2018");
            movieList.add(movie);
        }
        this.movieRepository.saveAll(movieList);
    }


    @Test
    public void testLast10MoviesFind() {
        assertThat(this.movieRepository.findTop10ByOrderByYearDesc())
            .isNotNull()
            .hasSize(10);
    }

    @Test
    public void testFindMoviesByYear() {
        Iterable<Movie> listMovies = this.movieRepository.findByYearOrderByName("2018");
        assertThat(listMovies)
            .isNotNull()
            .noneMatch(movie -> movie == null);

        System.out.println("SIZE::::::: "+((List<Movie>) listMovies).size());
        //Assert.assertTrue(((List<Movie>) listMovies).size() >= 10);
        assertThat(((List<Movie>) listMovies).size())
            .isGreaterThanOrEqualTo(10);
    }


    @Test
    public void testFindByGender() {
        Gender gender = new Gender();
        gender.setId(1);
        Iterable<Movie> listMoviesByGender = this.movieRepository.findByGenderByGender(gender);

        assertThat(listMoviesByGender)
            .isNotNull();

        Assert.assertTrue( ((List<Movie>)listMoviesByGender).size() >= 1 );
    }
}

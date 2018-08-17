package com.julianolarte.netflix.repositoryTest;

import com.julianolarte.netflix.models.*;
import com.julianolarte.netflix.projections.MovieProjection;
import com.julianolarte.netflix.repositories.*;
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
import java.sql.Date;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMovieRepository profileMovieRepository;


    @Before
    @Rollback(true)
    public void before() {

        //save user
        User user = new User();
        user.setEmail("gmail@gamil.com");
        user.setPassword("12345");
        user.setBirthdate(new Date(23, 12, 2018));
        this.userRepository.save(user);

        System.out.println(user.getId());
        //save profile
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setName("profile test");
        this.profileRepository.save(profile);


        //save one gender
        Gender gender = new Gender();
        gender.setDescription("gender for test in movies repository");
        this.genderRepository.save(gender);

        // save 10 movies for test
        List<Movie> movieList = new ArrayList<>();
        List<ProfileMovie> listProfileMovie = new ArrayList<>();
        Movie movie;
        ProfileMovie profileMovie;
        Date date = new Date(23, 12, 2018);
        for(int i = 0; i < 10; i++) {
            movie = new Movie();
            movie.setName("movie #" + i);
            movie.setDescription("Description Movie #" + i);
            movie.setDurationMin(120);
            movie.setLanguage("English");
            movie.setGender(gender);
            movie.setYear("2018");
            movieList.add(movie);

            profileMovie = new ProfileMovie();
            profileMovie.setMovie(movie);
            profileMovie.setProfile(profile);
            profileMovie.setWatchDate(date);
            listProfileMovie.add(profileMovie);
        }
        this.movieRepository.saveAll(movieList);
        this.profileMovieRepository.saveAll(listProfileMovie);
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
        Profile profile = new Profile();
        profile.setId(1);
        Iterable<MovieProjection> listMoviesByGender = this.movieRepository.findByGender(gender, profile);

        assertThat(listMoviesByGender)
            .isNotNull();

        Assert.assertTrue( ((List<MovieProjection>)listMoviesByGender).size() >= 1 );
    }


    @Test
    public void testFindByProfile() {
        Profile profile = new Profile();
        profile.setId(1);
        Iterable<MovieProjection> listMovieByProfile = this.movieRepository.findByProfile(profile);

        assertThat(listMovieByProfile)
                .isNotNull();

        Assert.assertTrue( ((List<MovieProjection>)listMovieByProfile).size() >= 1 );
    }

    @Test
    public void testFindMovieWithFavoriteByProfile() {
        Profile profile = new Profile();
        profile.setId(1);
        Iterable<MovieProjection> listMoviesByGender = this.movieRepository.findAllWithFavoriteByProfile(profile);

        assertThat(listMoviesByGender)
                .isNotNull();

        Assert.assertTrue( ((List<MovieProjection>)listMoviesByGender).size() >= 1 );
    }
}

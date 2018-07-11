package com.julianolarte.netflix.controllerTest;

import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.models.Movie;
import com.julianolarte.netflix.repositories.GenderRepository;
import com.julianolarte.netflix.repositories.MovieRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
        gender.setId(1);
        this.genderRepository.save(gender);

        // save 10 movies for test
        List<Movie> movieList = new ArrayList<>();
        Movie movie;
        for(int i = 0; i < 10; i++) {
            movie = new Movie();
            movie.setName("movie #" + i);
            movie.setDescription("Description Movie #" + i);
            movie.setDurationMin(120);
            movie.setLanguage("Espanish");
            movie.setGenderByGender(gender);
            movie.setYear("2018");
            movieList.add(movie);
        }
        this.movieRepository.saveAll(movieList);
    }


    @Test
    public void testMovieRoute() throws Exception {
        this.mockMvc.perform(get("/movie")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThanOrEqualTo(10)));
    }

    @Test
    public void testMovieLast10Route() throws Exception {
        this.mockMvc.perform(get("/movieLast10")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(Matchers.equalTo(10)));
    }


    @Test
    public void testMovieByYearRoute() throws Exception {
        this.mockMvc.perform(get("/movieByYear")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .param("year", "2018"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThanOrEqualTo(10)));
    }

    @Test
    public void testMovieByYearWithoutYearParamRoute() throws Exception {
        this.mockMvc.perform(get("/movieByYear")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testMovieByGenderRoute() throws Exception {
        this.mockMvc.perform(get("/movieByGender")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .param("gender", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThanOrEqualTo(10)));
    }

    @Test
    public void testMovieByGenderWithoutGenderParamRoute() throws Exception {
        this.mockMvc.perform(get("/movieByGender")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }
}

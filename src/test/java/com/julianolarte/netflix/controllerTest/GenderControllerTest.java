package com.julianolarte.netflix.controllerTest;

import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.repositories.GenderRepository;
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
public class GenderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenderRepository genderRepository;

    @Before
    @Rollback(true)
    public void before() {
        //save 10 gender's entities in DB
        List<Gender> listGender = new ArrayList<>();
        Gender gender;
        for(int i = 0; i < 10; i++) {
            gender = new Gender();
            gender.setDescription("this gender is a numer #" + i);
            listGender.add(gender);
        }
        this.genderRepository.saveAll(listGender);
    }


    @Test
    public void testGenderRoute() throws Exception {
        this.mockMvc.perform(get("/gender")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray());
    }
}

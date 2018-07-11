package com.julianolarte.netflix.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julianolarte.netflix.models.User;
import com.julianolarte.netflix.repositories.UserRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    @Rollback(true)
    public void before() {
        User user = new User();
        user.setEmail("email@gmail.com");
        user.setPassword("12345");

        this.userRepository.save(user);
    }

    @Test
    public void testLoginRoute() throws Exception {
        User user = new User();
        user.setEmail("email@gmail.com");
        user.setPassword("12345");

        String jsonUser = this.objectMapper.writeValueAsString(user);

        this.mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.email").value("email@gmail.com"));
    }

}

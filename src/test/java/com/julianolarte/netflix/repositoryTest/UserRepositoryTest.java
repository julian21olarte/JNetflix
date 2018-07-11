package com.julianolarte.netflix.repositoryTest;

import com.julianolarte.netflix.models.User;
import com.julianolarte.netflix.repositories.UserRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import javax.transaction.Transactional;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    private static boolean initialized = false;

    @Autowired
    private UserRepository userRepository;

    @Before
    @Rollback(true)
    public void beforeClass() {

        if(!initialized) {
            //create new User
            User user = new User();
            user.setEmail("email@gmail.com");
            user.setPassword("12345");

            //save User
            this.userRepository.save(user);
            initialized = true;
        }

    }

    @Test
    public void testLoginFindByEmailAndPassword() {
        User userLogged = this.userRepository
            .findByEmailAndPassword("email@gmail.com", "12345");

        assertThat(userLogged)
            .isNotNull();
        assertThat(userLogged.getEmail())
                .isEqualToIgnoringCase("email@gmail.com");
        assertThat(userLogged.getPassword())
                .isEqualToIgnoringCase("12345");

    }
}

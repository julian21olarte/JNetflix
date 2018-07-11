package com.julianolarte.netflix.repositoryTest;


import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.repositories.GenderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class GenderRepositoryTest {

    private static boolean initialized = false;

    @Autowired
    private GenderRepository genderRepository;

    @Before
    @Rollback(true)
    public void before() {

        if(!initialized) {

            //save 5 gender's entities in DB
            int i = 5;
            List<Gender> listGender = new ArrayList<>();
            Gender gender;
            while(--i > 0) {
                gender = new Gender();
                gender.setDescription("this gender is a numer #" + i);
                listGender.add(gender);
            }
            this.genderRepository.saveAll(listGender);
            initialized = true;
        }
    }


    @Test
    public void testGenderFindAll() {

        Iterable<Gender> listGender = this.genderRepository.findAll();

        List<Gender> genderListFilter = ((List<Gender>) listGender).stream()
            .filter(gender -> gender.getDescription().equals("this gender is a numer #1"))
            .collect(toList());

        Gender genderFiltered = genderListFilter.get(0);

        assertThat(listGender)
            .doesNotContainNull();

        assertThat(((List<Gender>)listGender).size())
            .isNotEqualTo(0);

        assertThat(genderListFilter)
            .hasSize(1);

        assertThat(genderFiltered)
            .isNotNull();

        assertThat(genderFiltered.getDescription())
            .isEqualToIgnoringCase("this gender is a numer #1");
    }
}

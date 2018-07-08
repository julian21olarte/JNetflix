package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.Gender;
import org.springframework.data.repository.CrudRepository;

public interface GenderRepository extends CrudRepository<Gender, Long> {

    /**
     * @param description
     * @return gender by description name
     */
    Iterable<Gender> findAllByDescription(String description);
}

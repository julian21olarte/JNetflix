package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * @param email
     * @param password
     * @return Usuario
     */
    User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}

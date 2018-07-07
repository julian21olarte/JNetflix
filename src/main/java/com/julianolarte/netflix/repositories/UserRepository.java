package com.julianolarte.netflix.repositories;

import com.julianolarte.netflix.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Usuario, Long> {

    /**
     * @param email
     * @param clave
     * @return Usuario
     */
    Usuario findByEmailAndClave(String email, String clave);
}

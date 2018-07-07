package com.julianolarte.netflix.controllers;

import com.julianolarte.netflix.models.Usuario;
import com.julianolarte.netflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Usuario login(@RequestBody Usuario userLogin) {
        return this.userRepository.findByEmailAndClave(userLogin.getEmail(), userLogin.getClave());
    }
}


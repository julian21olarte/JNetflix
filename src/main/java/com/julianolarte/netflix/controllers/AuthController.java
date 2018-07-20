package com.julianolarte.netflix.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julianolarte.netflix.models.User;
import com.julianolarte.netflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User login(@RequestBody User userLogin) {
        return this.userRepository.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean logout() {
        return true;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity register(@RequestBody User user ) throws JsonProcessingException {

        Map<String, Object> responseMap = new HashMap<>();
        if(this.userRepository.existsByEmail(user.getEmail())) {
            responseMap.put("error", true);
            responseMap.put("message", "El usuario que intenta registrar ya existe.");
            return new ResponseEntity(responseMap, HttpStatus.CONFLICT);
        }
        responseMap.put("user", this.objectMapper.writeValueAsString(this.userRepository.save(user)));
        responseMap.put("error", false);
        responseMap.put("message", "Usuario registrado correctamente.");
        return new ResponseEntity(responseMap, HttpStatus.OK);
    }
}


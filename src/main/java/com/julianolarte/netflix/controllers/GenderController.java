package com.julianolarte.netflix.controllers;

import com.julianolarte.netflix.models.Gender;
import com.julianolarte.netflix.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GenderController {

    @Autowired
    private GenderRepository genderRepository;

    @RequestMapping(value = "/gender", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Gender> getGenders() {
        return this.genderRepository.findAll();
    }
}

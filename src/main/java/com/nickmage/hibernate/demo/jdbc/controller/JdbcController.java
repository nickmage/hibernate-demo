package com.nickmage.hibernate.demo.jdbc.controller;

import com.nickmage.hibernate.demo.jdbc.model.Person;
import com.nickmage.hibernate.demo.jdbc.service.PersonJdbcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JdbcController {
    private final PersonJdbcService personJdbcService;

    public JdbcController(PersonJdbcService personJdbcService) {
        this.personJdbcService = personJdbcService;
    }

    @GetMapping("/persons")
    public List<Person> getAll() {
        return personJdbcService.getAll();
    }
}

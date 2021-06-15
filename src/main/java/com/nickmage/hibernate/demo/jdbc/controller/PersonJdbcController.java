package com.nickmage.hibernate.demo.jdbc.controller;

import com.nickmage.hibernate.demo.jdbc.model.Person;
import com.nickmage.hibernate.demo.jdbc.service.PersonJdbcService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonJdbcController {
    private final PersonJdbcService personJdbcService;

    public PersonJdbcController(PersonJdbcService personJdbcService) {
        this.personJdbcService = personJdbcService;
    }

    @GetMapping
    public List<Person> getAll() {
        return personJdbcService.getAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable int id) {
        return personJdbcService.getById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        personJdbcService.create(person);
        return person;
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable int id, @RequestBody Person person) {
        personJdbcService.update(id, person);
        return person;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        personJdbcService.deleteById(id);
        return "Person with id " + id + " has been deleted.";
    }
}

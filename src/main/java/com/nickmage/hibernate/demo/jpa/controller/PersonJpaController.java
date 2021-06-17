package com.nickmage.hibernate.demo.jpa.controller;

import com.nickmage.hibernate.demo.jpa.entity.Person;
import com.nickmage.hibernate.demo.jpa.service.PersonJpaService;
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
@RequestMapping("/jpa/persons")
public class PersonJpaController {
    private final PersonJpaService personJpaService;

    public PersonJpaController(PersonJpaService personJpaService) {
        this.personJpaService = personJpaService;
    }

    @GetMapping
    public List<Person> getAll() {
        return personJpaService.getAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable int id) {
        return personJpaService.getById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personJpaService.create(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable int id, @RequestBody Person person) {
        return personJpaService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        personJpaService.deleteById(id);
        return "Person with id " + id + " has been deleted.";
    }
}

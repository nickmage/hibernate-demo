package com.nickmage.hibernate.demo.jpa.service;

import com.nickmage.hibernate.demo.jpa.repository.PersonJpaRepository;
import com.nickmage.hibernate.demo.jpa.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonJpaService {
    private final PersonJpaRepository personJpaRepository;

    public PersonJpaService(PersonJpaRepository personJpaRepository) {
        this.personJpaRepository = personJpaRepository;
    }

    public List<Person> getAll(){
        return personJpaRepository.findAll();
    }

    public Person getById(int id){
        return personJpaRepository.findById(id);
    }

    public void create(Person person) {
        personJpaRepository.create(person);
    }

    public void update(int id, Person person) {
        personJpaRepository.update(id, person);
    }

    public int deleteById(int id) {
        return personJpaRepository.deleteById(id);
    }
}

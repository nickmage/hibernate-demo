package com.nickmage.hibernate.demo.jpa.service;

import com.nickmage.hibernate.demo.jpa.repository.PersonJpaRepository;
import com.nickmage.hibernate.demo.jpa.entity.Person;
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

    public Person create(Person person) {
        return personJpaRepository.save(person);
    }

    public Person update(int id, Person person) {
        person.setId(id);
        return personJpaRepository.save(person);
    }

    public void deleteById(int id) {
        personJpaRepository.deleteById(id);
    }
}

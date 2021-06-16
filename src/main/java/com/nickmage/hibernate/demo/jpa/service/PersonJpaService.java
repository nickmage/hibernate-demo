package com.nickmage.hibernate.demo.jpa.service;

import com.nickmage.hibernate.demo.jpa.dao.PersonJpaDao;
import com.nickmage.hibernate.demo.jpa.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonJpaService {
    private final PersonJpaDao personJpaDao;

    public PersonJpaService(PersonJpaDao personJpaDao) {
        this.personJpaDao = personJpaDao;
    }

    public List<Person> getAll(){
        return personJpaDao.findAll();
    }

    public Person getById(int id){
        return personJpaDao.findById(id);
    }

    public void create(Person person) {
        personJpaDao.create(person);
    }

    public void update(int id, Person person) {
        personJpaDao.update(id, person);
    }

    public int deleteById(int id) {
        return personJpaDao.deleteById(id);
    }
}

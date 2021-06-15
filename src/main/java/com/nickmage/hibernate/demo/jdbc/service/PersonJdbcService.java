package com.nickmage.hibernate.demo.jdbc.service;

import com.nickmage.hibernate.demo.jdbc.dao.PersonJdbcDao;
import com.nickmage.hibernate.demo.jdbc.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonJdbcService {
    private final PersonJdbcDao personJdbcDao;

    public PersonJdbcService(PersonJdbcDao personJdbcDao) {
        this.personJdbcDao = personJdbcDao;
    }

    public List<Person> getAll(){
        return personJdbcDao.findAll();
    }

    public Person getById(int id){
        return personJdbcDao.findById(id);
    }

    public void create(Person person) {
        personJdbcDao.create(person);
    }

    public void update(int id, Person person) {
        personJdbcDao.update(id, person);
    }

    public int deleteById(int id) {
        return personJdbcDao.deleteById(id);
    }
}

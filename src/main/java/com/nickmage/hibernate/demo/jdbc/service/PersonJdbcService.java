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
}

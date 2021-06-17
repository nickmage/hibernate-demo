package com.nickmage.hibernate.demo.jpa.repository;

import com.nickmage.hibernate.demo.jpa.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional //Better to use it on business logic services
public class PersonJpaRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public PersonJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Person> findAll() {
        return entityManager.createNamedQuery("findAll", Person.class).getResultList();
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person save(Person person) {
        return entityManager.merge(person);
    }

    public void deleteById(int id) {
        entityManager.remove(findById(id));
    }
}

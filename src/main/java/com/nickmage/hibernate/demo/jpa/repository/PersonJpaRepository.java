package com.nickmage.hibernate.demo.jpa.repository;

import com.nickmage.hibernate.demo.jpa.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional //Better to use it on a business logic services
public class PersonJpaRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Person> rowMapper = (resultSet, rowNumber) -> {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setLocation(resultSet.getString("location"));
        person.setBirthDate(resultSet.getTimestamp("birth_date").toLocalDateTime().toLocalDate());
        return person;
    };

    public PersonJpaRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person order by id asc", rowMapper);
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public void create(Person person) {
        jdbcTemplate.update("insert into person (id, name, location, birth_date) values (?, ?, ?, ?)",
                person.getId(), person.getName(), person.getLocation(), person.getBirthDate());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("update person set name = ?, location = ?, birth_date = ? where id = ?",
                person.getName(), person.getLocation(), person.getBirthDate(), id);
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("delete from person where id = ?", id);
    }
}

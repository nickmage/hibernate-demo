package com.nickmage.hibernate.demo.jdbc.dao;

import com.nickmage.hibernate.demo.jdbc.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Person> rowMapper = (resultSet, rowNumber) -> {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setLocation(resultSet.getString("location"));
        person.setBirthDate(resultSet.getTimestamp("birth_date").toLocalDateTime().toLocalDate());
        return person;
    };

    public PersonJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person order by id asc", rowMapper);
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject("select * from person where id = ?", rowMapper, id);
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

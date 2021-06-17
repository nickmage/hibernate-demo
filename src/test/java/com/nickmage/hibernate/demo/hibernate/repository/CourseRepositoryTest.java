package com.nickmage.hibernate.demo.hibernate.repository;

import com.nickmage.hibernate.demo.hibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
public class CourseRepositoryTest {

    private static final Long PRE_SAVED_ID = 1001L;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldReturn() {
        Course actual = new Course("Math");
        actual.setId(PRE_SAVED_ID);

        assertThat(courseRepository.findById(PRE_SAVED_ID), is(actual));
    }

    @Test
    void contextLoads() {
        Long id = 1L;
        Course actual = new Course("Math");
        actual.setId(id);

        assertThat(courseRepository.findById(id), is(nullValue()));
    }

}

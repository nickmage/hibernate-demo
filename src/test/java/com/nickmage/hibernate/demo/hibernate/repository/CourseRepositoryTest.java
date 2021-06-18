package com.nickmage.hibernate.demo.hibernate.repository;

import com.nickmage.hibernate.demo.hibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
public class CourseRepositoryTest {

    private static final Long PRE_SAVED_ID_TO_FIND = 1001L;
    private static final Long PRE_SAVED_ID_TO_UPDATE = 1002L;
    private static final Long PRE_SAVED_ID_TO_DELETE = 1003L;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldFindCourse() {
        Course actual = new Course("Math");
        actual.setId(PRE_SAVED_ID_TO_FIND);

        assertThat(courseRepository.findById(PRE_SAVED_ID_TO_FIND), is(actual));
    }

    @Test
    void shouldNotReturnCourse() {
        Long id = 1L;
        Course actual = new Course("Math");
        actual.setId(id);

        assertThat(courseRepository.findById(id), is(nullValue()));
    }

    @Test
    @Transactional
    void shouldDeleteCourse() {
        courseRepository.deleteById(PRE_SAVED_ID_TO_DELETE);

        assertThat(courseRepository.findById(PRE_SAVED_ID_TO_DELETE), is(nullValue()));
    }

}

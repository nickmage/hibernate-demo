package com.nickmage.hibernate.demo.hibernate.repository;

import com.nickmage.hibernate.demo.hibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
@DirtiesContext
public class CourseRepositoryTest {

    private static final Long PRE_SAVED_ID_TO_FIND = 1001L;
    private static final Long PRE_SAVED_ID_TO_UPDATE = 1002L;
    private static final Long PRE_SAVED_ID_TO_DELETE = 1003L;
    private static final String COURSE_NAME_TO_FIND = "Math";
    private static final String COURSE_NAME_TO_SAVE = "Art";

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldFindCourse() {
        Course actual = new Course(COURSE_NAME_TO_FIND);
        actual.setId(PRE_SAVED_ID_TO_FIND);

        assertThat(courseRepository.findById(PRE_SAVED_ID_TO_FIND), is(actual));
    }

    @Test
    void shouldNotReturnCourse() {
        Long id = 1L;
        Course actual = new Course(COURSE_NAME_TO_FIND);
        actual.setId(id);

        assertThat(courseRepository.findById(id), is(nullValue()));
    }

    @Test
    @Transactional
    void shouldCreateCourse() {
        Course courseToCreate = new Course(COURSE_NAME_TO_SAVE);
        courseRepository.save(courseToCreate);

        assertThat(courseRepository.findById(courseToCreate.getId()), is(courseToCreate));
    }

    @Test
    @Transactional
    void shouldUpdateCourse() {
        Course courseToUpdate = courseRepository.findById(PRE_SAVED_ID_TO_UPDATE);
        courseToUpdate.setName(COURSE_NAME_TO_SAVE);
        //courseRepository.save(courseToUpdate);

        assertThat(courseRepository.findById(PRE_SAVED_ID_TO_UPDATE), is(courseToUpdate));
    }

    @Test
    @Transactional
    void shouldDeleteCourse() {
        courseRepository.deleteById(PRE_SAVED_ID_TO_DELETE);

        assertThat(courseRepository.findById(PRE_SAVED_ID_TO_DELETE), is(nullValue()));
    }

}

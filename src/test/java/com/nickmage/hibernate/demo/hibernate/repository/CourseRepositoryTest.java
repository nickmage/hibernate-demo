package com.nickmage.hibernate.demo.hibernate.repository;

import com.nickmage.hibernate.demo.hibernate.entity.Course;
import com.nickmage.hibernate.demo.hibernate.entity.Review;
import com.nickmage.hibernate.demo.hibernate.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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
    @Autowired
    private EntityManager entityManager;

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

    @Test
    @Transactional
    void shouldRetrieveCourseReviews() {
        Course course = courseRepository.findById(PRE_SAVED_ID_TO_FIND);

        List<Review> reviews = course.getReviews();

        assertThat(reviews, is(notNullValue()));
        assertThat(reviews.size(), is(2));
    }

    @Test
    @Transactional
    void shouldRetrieveStudentsOfCourse() {
        Course course = entityManager.find(Course.class, PRE_SAVED_ID_TO_FIND);
        List<Student> students = course.getStudents();
        assertThat(students, is(notNullValue()));
        assertThat(students.size(), is(2));
    }

    @Test
    void shouldNotReturnCourseWithCache() {
        Course course = courseRepository.findByIdWithCache(PRE_SAVED_ID_TO_FIND);

        assertThat(course, is(notNullValue()));
    }
}

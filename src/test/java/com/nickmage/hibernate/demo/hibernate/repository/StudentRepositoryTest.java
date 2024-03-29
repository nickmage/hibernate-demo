package com.nickmage.hibernate.demo.hibernate.repository;

import com.nickmage.hibernate.demo.hibernate.entity.Course;
import com.nickmage.hibernate.demo.hibernate.entity.Passport;
import com.nickmage.hibernate.demo.hibernate.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
@DirtiesContext
public class StudentRepositoryTest {

    private static final Long PRE_SAVED_ID_TO_FIND = 1001L;
    private static final Long PRE_SAVED_ID_TO_UPDATE = 1002L;
    private static final Long PRE_SAVED_ID_TO_DELETE = 1003L;
    private static final String STUDENT_NAME_TO_FIND = "Leon";
    private static final String COURSE_NAME_TO_SAVE = "Arthur";

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldFindStudent() {
        Passport passport = new Passport("MP145364NT");
        passport.setId(2001L);
        Student actual = new Student(STUDENT_NAME_TO_FIND);
        actual.setId(PRE_SAVED_ID_TO_FIND);
        actual.setPassport(passport);

        assertThat(studentRepository.findById(PRE_SAVED_ID_TO_FIND), is(actual));
    }

    @Test
    void shouldNotReturnStudent() {
        Long id = 1L;
        Student actual = new Student(STUDENT_NAME_TO_FIND);
        actual.setId(id);

        assertThat(studentRepository.findById(id), is(nullValue()));
    }

    @Test
    @Transactional
    void shouldCreateStudent() {
        Student studentToCreate = new Student(COURSE_NAME_TO_SAVE);
        studentRepository.save(studentToCreate);

        assertThat(studentRepository.findById(studentToCreate.getId()), is(studentToCreate));
    }

    @Test
    @Transactional
    @Rollback
    void shouldUpdateStudent() {
        Student studentToUpdate = studentRepository.findById(PRE_SAVED_ID_TO_UPDATE);
        studentToUpdate.setName(COURSE_NAME_TO_SAVE);

        assertThat(studentRepository.findById(PRE_SAVED_ID_TO_UPDATE), is(studentToUpdate));
    }

    @Test
    @Transactional
    void shouldDeleteStudent() {
        studentRepository.deleteById(PRE_SAVED_ID_TO_DELETE);

        assertThat(studentRepository.findById(PRE_SAVED_ID_TO_DELETE), is(nullValue()));
    }

    @Test
    @Transactional //important for lazy fetch type
    void shouldRetrieveStudentWithPassport() {
        Student student = studentRepository.findById(PRE_SAVED_ID_TO_FIND);
        assertThat(student, is(notNullValue()));
        assertThat(student.getPassport(), is(notNullValue()));
    }

    @Test
    @Transactional
    void shouldRetrievePassportWithStudent() {
        Passport passport = entityManager.find(Passport.class, 2001L);
        assertThat(passport, is(notNullValue()));
        assertThat(passport.getStudent(), is(notNullValue()));
    }

    @Test
    @Transactional
    void shouldRetrieveCourseOfStudents() {
        Student student = studentRepository.findById(PRE_SAVED_ID_TO_FIND);
        List<Course> courses = student.getCourses();
        assertThat(courses, is(notNullValue()));
        assertThat(courses.size(), is(1));
    }
}

package com.nickmage.hibernate.demo.hibernate.repository;

import com.nickmage.hibernate.demo.hibernate.entity.Course;
import com.nickmage.hibernate.demo.hibernate.entity.Passport;
import com.nickmage.hibernate.demo.hibernate.entity.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class StudentRepository {

    private final EntityManager entityManager;

    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Student> findAll() {
        return entityManager.createQuery("select c from Student c", Student.class).getResultList();
    }

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            student = entityManager.merge(student);
        }
        return student;
    }

    public List<Student> findCoursesByPassportNumberPattern(String pattern) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.passport.number like :pattern", Student.class);
        query.setParameter("pattern", pattern);
        return query.getResultList();
    }

    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    public void addCourseToStudent(Long studentId, Course course) {
        Student student = findById(studentId);
        student.getCourses().add(course);
    }

    @Transactional
    public void saveStudentWithPassport() {
        Passport passport = new Passport("GA102048KR");
        entityManager.persist(passport);

        Student student = new Student("Test student");
        student.setPassport(passport);
        entityManager.persist(student);
    }

    @Transactional
    public void implicitUpdateWithRefresh() {
        //Request to create a record
        Student student1 = new Student("Test student 1");
        entityManager.persist(student1);
        Student student2 = new Student("Test student 2");
        entityManager.persist(student2);

        //Send changes to the DB
        entityManager.flush();

        student1.setName("Test student 1 new");

        //Refresh record
        System.err.println("Before refresh: " + student1.getName());
        entityManager.refresh(student1);
        System.err.println("After refresh: " + student1.getName());
    }
}

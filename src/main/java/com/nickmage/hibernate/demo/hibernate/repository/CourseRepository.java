package com.nickmage.hibernate.demo.hibernate.repository;

import com.nickmage.hibernate.demo.hibernate.entity.Course;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CourseRepository {

    private final EntityManager entityManager;

    public CourseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Course> findAll() {
        return entityManager.createQuery("select c from Course c", Course.class).getResultList();
    }

    public Course findById(Long id) {
        //Alternative findById query
        /*Query query = entityManager.createNativeQuery("select * from course where id = ?/:id", Course.class);
        Query.setParameter(1/"id", 1001L);
        return query.getResultList();*/
        return entityManager.find(Course.class, id);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            course = entityManager.merge(course);
        }
        return course;
    }

    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    @Transactional
    public void implicitUpdate() {
        Course course1 = new Course("Test course 1");
        entityManager.persist(course1);
        //Save to the DB
        entityManager.flush();

        //This one will be also saved
        course1.setName("Test course 1 new");

        //Save to the DB
        Course course2 = new Course("Test course 2");
        entityManager.persist(course2);
        entityManager.flush();

        //Remove from the persistence context
        entityManager.detach(course2);

        //Will not be saved
        course2.setName("Test course 2 new");
        entityManager.flush();
    }

    @Transactional
    public void implicitUpdateWithRefresh() {
        //Request to create a record
        Course course1 = new Course("Test course 1");
        entityManager.persist(course1);
        Course course2 = new Course("Test course 2");
        entityManager.persist(course2);

        //Send changes to the DB
        entityManager.flush();

        course1.setName("Test course 1 new");

        //Refresh record
        System.err.println("Before refresh: " + course1.getName());
        entityManager.refresh(course1);
        System.err.println("After refresh: " + course1.getName());
    }
}

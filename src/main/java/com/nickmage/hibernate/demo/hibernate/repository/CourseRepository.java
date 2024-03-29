package com.nickmage.hibernate.demo.hibernate.repository;

import com.nickmage.hibernate.demo.hibernate.entity.Course;
import com.nickmage.hibernate.demo.hibernate.entity.Review;
import com.nickmage.hibernate.demo.hibernate.entity.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CourseRepository {

    private final EntityManager entityManager;

    public CourseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Course> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        entityManager.createQuery(query.select(root)).getResultList();
        return entityManager.createQuery("select c from Course c", Course.class).getResultList();
    }

    public Course findById(Long id) {
        //Alternative findById query
        /*Query query = entityManager.createNativeQuery("select * from course where id = ?/:id", Course.class);
        query.setParameter(1/"id", 1001L);
        return query.getResultList();*/
        return entityManager.find(Course.class, id);
    }

    public List<Course> findCoursesWithoutStudents() {
        /*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.where(criteriaBuilder.isEmpty(root.get("students")));
        entityManager.createQuery(query.select(root)).getResultList();*/
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.students is empty", Course.class);
        return query.getResultList();
    }

    public List<Course> findCoursesWithStudentsMoreThan(int quantity) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where size(c.students) >= :quantity", Course.class);
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }

    public List<Course> findCoursesOrderByStudents() {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c order by size(c.students)", Course.class);
        return query.getResultList();
    }

    public List<Course> findCoursesByNamePattern(String pattern) {
        /*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.where(criteriaBuilder.like(root.get("name"), pattern));
        entityManager.createQuery(query.select(root)).getResultList();*/
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.name like :pattern", Course.class);
        query.setParameter("pattern", pattern);
        return query.getResultList();
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
    public void addReviewsToCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        reviews.forEach(review -> {
            review.setCourse(course);
            entityManager.persist(review);
        });
    }

    public void addStudentToCourse(Long courseId, Student student) {
        Course course = findById(courseId);
        student.getCourses().add(course);
    }

    @Transactional
    public void implicitUpdate() {
        Course course1 = new Course("Test course 1");
        //Make entity managed/persistent
        entityManager.persist(course1);
        //Save to the DB
        entityManager.flush();

        //This one will be also saved soon
        course1.setName("Test course 1 new");

        //Save to the DB course2 and name of course1
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

    //@Transactional
    public Course findByIdWithCache(Long id) {
        //Will execute select query
        Course course = findById(1001L);
        System.err.println(course);
        //Will use cached value, query won't be executed unless it is within the same transaction
        course = findById(1001L);
        System.err.println(course);
        return course;
    }
}

package com.nickmage.hibernate.demo.hibernate.service;

import com.nickmage.hibernate.demo.hibernate.entity.Course;
import com.nickmage.hibernate.demo.hibernate.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public Course getById(Long id){
        return courseRepository.findById(id);
    }

    public Course create(Course person) {
        return courseRepository.save(person);
    }

    public Course update(Long id, Course course) {
        course.setId(id);
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}

package com.nickmage.hibernate.demo.hibernate.repository;

import com.nickmage.hibernate.demo.hibernate.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final EntityManager entityManager;

    public EmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Employee> findAll() {
        return entityManager.createQuery("select e from FullTimeEmployee e", Employee.class).getResultList();
    }

    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            entityManager.persist(employee);
        } else {
            employee = entityManager.merge(employee);
        }
        return employee;
    }

    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}

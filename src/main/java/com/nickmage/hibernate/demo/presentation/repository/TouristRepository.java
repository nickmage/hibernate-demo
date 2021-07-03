package com.nickmage.hibernate.demo.presentation.repository;

import com.nickmage.hibernate.demo.presentation.entity.Tourist;
import com.nickmage.hibernate.demo.presentation.entity.Visa;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TouristRepository {

    private final EntityManager entityManager;

    public TouristRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Tourist> findAll() {
        return entityManager.createQuery("select t from Tourist t", Tourist.class).getResultList();
    }

    public Tourist findById(Long id) {
        return entityManager.find(Tourist.class, id);
    }

    public Tourist save(Tourist tourist) {
        if (tourist.getId() == null) {
            entityManager.persist(tourist);
        } else {
            tourist = entityManager.merge(tourist);
        }
        return tourist;
    }

    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    public void addVisaToTourist(Long studentId, Visa visa) {
        Tourist tourist = findById(studentId);
        tourist.getVisas().add(visa);
    }

    @Transactional
    public void implicitUpdateWithRefresh() {
        //Request to create a record
        Tourist tourist1 = new Tourist("Test tourist 1");
        entityManager.persist(tourist1);
        Tourist tourist2 = new Tourist("Test tourist 2");
        entityManager.persist(tourist2);

        //Send changes to the DB
        entityManager.flush();

        tourist1.setName("Test student 1 new");

        //Refresh record
        System.err.println("Before refresh: " + tourist1.getName());
        entityManager.refresh(tourist1);
        System.err.println("After refresh: " + tourist1.getName());
    }
}

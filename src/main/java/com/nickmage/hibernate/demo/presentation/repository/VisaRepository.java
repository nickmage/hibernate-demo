package com.nickmage.hibernate.demo.presentation.repository;

import com.nickmage.hibernate.demo.presentation.entity.Tourist;
import com.nickmage.hibernate.demo.presentation.entity.Visa;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class VisaRepository {

    private final EntityManager entityManager;

    public VisaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Visa> findAll() {
        return entityManager.createQuery("select v from Visa v", Visa.class).getResultList();
    }

    public Visa findById(Long id) {
        return entityManager.find(Visa.class, id);
    }

    public Visa save(Visa visa) {
        if (visa.getId() == null) {
            entityManager.persist(visa);
        } else {
            visa = entityManager.merge(visa);
        }
        return visa;
    }

    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    public void addTouristToVisa(Long visaId, Tourist tourist) {
        Visa visa = findById(visaId);
        tourist.getVisas().add(visa);
    }

    @Transactional
    public void implicitUpdate() {
        Visa visa1 = new Visa("Test visa 1");
        //Make entity managed/persistent
        entityManager.persist(visa1);
        //Save to the DB
        entityManager.flush();

        //This one will be also saved soon
        visa1.setCountryName("Test country new");

        //Save to the DB visa2 and name of visa1
        Visa visa2 = new Visa("Test visa 2");
        entityManager.persist(visa2);
        entityManager.flush();

        //Remove from the persistence context
        entityManager.detach(visa2);

        //Will not be saved
        visa2.setCountryName("Test visa 2 new");
        entityManager.flush();
    }

    @Transactional
    public void implicitUpdateWithRefresh() {
        //Request to create a record
        Visa visa1 = new Visa("Test visa 1");
        entityManager.persist(visa1);
        Visa visa2 = new Visa("Test visa 2");
        entityManager.persist(visa2);

        //Send changes to the DB
        entityManager.flush();

        visa1.setCountryName("Test visa 1 new");

        //Refresh record
        System.err.println("Before refresh: " + visa1.getCountryName());
        entityManager.refresh(visa1);
        System.err.println("After refresh: " + visa1.getCountryName());
    }

    //@Transactional
    public Visa findByIdWithCache(Long id) {
        //Will execute select query
        Visa visa = findById(1001L);
        System.err.println(visa);
        //Will use cached value, query won't be executed unless it is within the same transaction
        visa = findById(1001L);
        System.err.println(visa);
        return visa;
    }
}

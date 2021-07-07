package com.nickmage.hibernate.demo.presentation.repository;

import com.nickmage.hibernate.demo.presentation.entity.Tourist;
import com.nickmage.hibernate.demo.presentation.entity.Visa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@DirtiesContext
public class VisaRepositoryTest {

    @Autowired
    private VisaRepository visaRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void persistenceContextTest() {
        visaRepository.implicitUpdate();
    }

    @Test
    void persistenceContextRelationshipTest() {
        visaRepository.implicitUpdateWithRelationship(1001L);
    }

    @Test
    void persistenceContextWithRefreshTest() {
        visaRepository.implicitUpdateWithRefresh();
    }

    @Test
    //@Transactional
    void l2CacheTest() {
        visaRepository.findByIdWithCache(1001L);
    }

    @Test
    //@Transactional
    void nPlus1ProblemDemoTest() {
        List<Visa> visas = visaRepository.findAll();
        List<Tourist> tourists = new ArrayList<>();
        visas.forEach((visa) -> {
            tourists.addAll(visa.getTourists());
        });
        System.out.println(tourists);
    }

    @Test
    @Transactional
    void nPlus1WithEntityGraphSolvingTest() {
        List<Visa> visas = visaRepository.findAllWithGraph();
        List<Tourist> tourists = new ArrayList<>();
        visas.forEach((visa) -> {
            tourists.addAll(visa.getTourists());
        });
        System.err.println(tourists);
    }

    @Test
    @Transactional
    void nPlus1WithJoinFetchSolvingTest() {
        List<Visa> visas = visaRepository.findAllWithJoinFetch();
        List<Tourist> tourists = new ArrayList<>();
        visas.forEach((visa) -> {
            tourists.addAll(visa.getTourists());
        });
        System.err.println(tourists);
    }
}

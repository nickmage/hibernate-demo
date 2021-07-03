package com.nickmage.hibernate.demo.presentation.repository;

import com.nickmage.hibernate.demo.presentation.entity.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristJpaRepository extends JpaRepository<Tourist, Long> {
}

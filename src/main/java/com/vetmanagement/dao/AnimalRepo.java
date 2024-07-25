package com.vetmanagement.dao;

import com.vetmanagement.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
    Page<Animal> findByCustomerId(Long customerId, Pageable pageable);
}

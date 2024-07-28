package com.vetmanagement.dao;

import com.vetmanagement.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Animal entities.
 * It extends JpaRepository to provide CRUD operations
 * and additional query methods for Animal entities.
 */
@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {

    /**
     * Finds a page of Animal entities by the given customer ID.
     *
     * @param customerId the ID of the customer whose animals are to be found
     * @param pageable   the pagination information
     * @return a page of Animal entities associated with the specified customer ID
     */
    Page<Animal> findByCustomerId(Long customerId, Pageable pageable);

    /**
     * Finds a page of Animal entities by the given name.
     *
     * @param name     the name of the animals to be found
     * @param pageable the pagination information
     * @return a page of Animal entities with the specified name
     */
    Page<Animal> findByName(String name, Pageable pageable);
}

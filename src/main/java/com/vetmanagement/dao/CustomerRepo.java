package com.vetmanagement.dao;

import com.vetmanagement.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Customer entities.
 * It extends JpaRepository to provide CRUD operations
 * and additional query methods for Customer entities.
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    /**
     * Finds a page of Customer entities by their name.
     *
     * @param name     the name of the customer
     * @param pageable the pagination information
     * @return a page of Customer entities matching the specified name
     */
    Page<Customer> findByName(String name, Pageable pageable);
}

package com.vetmanagement.business.concretes;

import com.vetmanagement.business.abstracts.ICustomerService;
import com.vetmanagement.core.exception.NotFoundException;
import com.vetmanagement.core.utilies.Msg;
import com.vetmanagement.dao.CustomerRepo;
import com.vetmanagement.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;

    public CustomerManager(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    /**
     * Retrieve a customer by its ID.
     *
     * @param id Customer ID
     * @return Customer entity
     */
    @Override
    public Customer get(Long id) {
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    /**
     * Get a paginated list of customers.
     *
     * @param page     Page number
     * @param pageSize Number of items per page
     * @return Page of Customer entities
     */
    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerRepo.findAll(pageable);
    }

    /**
     * Save a new customer.
     *
     * @param customer Customer entity to save
     * @return Saved Customer entity
     */
    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    /**
     * Update an existing customer.
     *
     * @param customer Customer entity to update
     * @return Updated Customer entity
     */
    @Override
    public Customer update(Customer customer) {
        return this.customerRepo.save(customer);
    }

    /**
     * Delete a customer by its ID.
     *
     * @param id Customer ID
     * @return true if the deletion is successful
     */
    @Override
    public boolean delete(Long id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }

    /**
     * Get a paginated list of customers by their name.
     *
     * @param name     Customer name
     * @param page     Page number
     * @param pageSize Number of items per page
     * @return Page of Customer entities
     */
    @Override
    public Page<Customer> getCustomerByCustomerName(String name, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerRepo.findByName(name, pageable);
    }
}

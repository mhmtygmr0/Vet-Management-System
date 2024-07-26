package com.vetmanagement.business.abstracts;

import com.vetmanagement.entities.Customer;
import org.springframework.data.domain.Page;

public interface ICustomerService {
    Customer get(Long id);

    Page<Customer> cursor(int page, int pageSize);

    Customer save(Customer customer);

    Customer update(Customer customer);

    boolean delete(Long id);

    Page<Customer> getCustomerByCustomerName(String name, int page, int pageSize);
}

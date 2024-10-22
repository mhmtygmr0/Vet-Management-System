package com.vetmanagement.api;

import com.vetmanagement.business.abstracts.ICustomerService;
import com.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.vetmanagement.core.result.Result;
import com.vetmanagement.core.result.ResultData;
import com.vetmanagement.core.utilies.ResultHelper;
import com.vetmanagement.dto.request.customer.CustomerSaveRequest;
import com.vetmanagement.dto.request.customer.CustomerUpdateRequest;
import com.vetmanagement.dto.response.CursorResponse;
import com.vetmanagement.dto.response.CustomerResponse;
import com.vetmanagement.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    /**
     * Get customer by ID
     *
     * @param id Customer ID
     * @return CustomerResponse containing customer details
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") Long id) {
        Customer customer = this.customerService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    /**
     * Get a paginated list of customers
     *
     * @param page     Page number
     * @param pageSize Number of items per page
     * @return Paginated list of CustomerResponse
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Customer> customerPage = this.customerService.cursor(page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage.map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));
        return ResultHelper.cursor(customerResponsePage);
    }

    /**
     * Save a new customer
     *
     * @param customerSaveRequest Request containing details of the customer to save
     * @return CustomerResponse containing saved customer details
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerService.save(saveCustomer);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));
    }

    /**
     * Update an existing customer
     *
     * @param customerUpdateRequest Request containing updated details of the customer
     * @return CustomerResponse containing updated customer details
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        this.customerService.get(customerUpdateRequest.getId());
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerService.save(updateCustomer);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));
    }

    /**
     * Delete a customer by ID
     *
     * @param id Customer ID
     * @return Result indicating the status of the operation
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return ResultHelper.ok();
    }

    /**
     * Get customers by name
     *
     * @param name     Customer name
     * @param page     Page number
     * @param pageSize Number of items per page
     * @return Paginated list of CustomerResponse
     */
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> getCustomersByCustomerName(
            @PathVariable("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Customer> customerPage = this.customerService.getCustomerByCustomerName(name, page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage.map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));
        return ResultHelper.cursor(customerResponsePage);
    }
}

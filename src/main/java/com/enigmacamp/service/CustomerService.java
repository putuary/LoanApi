package com.enigmacamp.service;

import com.enigmacamp.model.entity.Customer;
import com.enigmacamp.model.request.CustomerRequest;
import com.enigmacamp.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse getCustomerById(String id);
    List<CustomerResponse> getAllCustomer();
    Customer saveCustomer(Customer customer);
    CustomerResponse updateCustomer(Customer customer);
    void deleteCustomer(String id);

}

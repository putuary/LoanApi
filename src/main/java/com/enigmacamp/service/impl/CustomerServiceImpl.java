package com.enigmacamp.service.impl;

import com.enigmacamp.model.entity.Customer;
import com.enigmacamp.model.entity.Role;
import com.enigmacamp.model.entity.User;
import com.enigmacamp.model.response.CustomerResponse;
import com.enigmacamp.model.response.UserResponse;
import com.enigmacamp.repository.CustomerRepository;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.utils.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse getCustomerById(String id) {
        if(customerRepository.findById(id).isPresent()) {
            Customer customer = customerRepository.findById(id).get();

            return CustomerResponse.builder()
                    .id(customer.getId())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .dateOfBirth(customer.getDateOfBirth())
                    .phone(customer.getPhone())
                    .status(customer.getStatus())
                    .build();
        } else {
            throw new RuntimeException("Customer with id" + id + " not found");
        }
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customer : customers) {
            customerResponses.add(CustomerResponse.builder()
                    .id(customer.getId())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .dateOfBirth(customer.getDateOfBirth())
                    .phone(customer.getPhone())
                    .status(customer.getStatus())
                    .build());
        }
        return customerResponses;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerResponse updateCustomer(Customer customer) {
        if(customerRepository.findById(customer.getId()).isPresent()) {
            Customer customerUpdate = saveCustomer(customer);
            return CustomerResponse.builder()
                    .id(customerUpdate.getId())
                    .firstName(customerUpdate.getFirstName())
                    .lastName(customerUpdate.getLastName())
                    .dateOfBirth(customerUpdate.getDateOfBirth())
                    .phone(customerUpdate.getPhone())
                    .status(customerUpdate.getStatus())
                    .build();
        } else {
            throw new RuntimeException("Customer with id" + customer.getId() + " not found");
        }
    }

    @Override
    public void deleteCustomer(String id) {
        if(customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
        } else{
            throw new RuntimeException("Customer with id" + id + " not found");
        }
    }
}

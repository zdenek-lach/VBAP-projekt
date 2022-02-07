package io.github.zdeneklach.vbap.service;

import io.github.zdeneklach.vbap.entity.Customer;
import io.github.zdeneklach.vbap.exception.DbException;
import io.github.zdeneklach.vbap.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> allCustomers = null;
        try {
            allCustomers = customerRepository.findAll();
        } catch (Exception e) {
            throw new DbException("Failed to retrieve all customers from DB.");
        }
        return allCustomers;
    }
}

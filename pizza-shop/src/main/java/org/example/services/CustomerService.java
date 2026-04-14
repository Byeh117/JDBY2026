package org.example.services;

import org.example.entities.Customers;
import org.example.repositories.CustomerRepository;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Customers customers) {
        customerRepository.save(customers);
        System.out.println("Customer added: " + customers);
    }

    public void displayCustomers() {
        customerRepository.getAllCustomers().forEach(System.out::println);
    }
}

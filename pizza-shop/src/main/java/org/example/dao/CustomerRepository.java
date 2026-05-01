package org.example.dao;

import org.example.model.Customers;

import java.util.List;

public class CustomerRepository {
    private final PseudoDatabase database;

    public CustomerRepository(PseudoDatabase database) {
        this.database = database;
    }

    public void save(Customers customers) {
        database.getCustomer().add(customers);
    }

    public List<Customers> getAllCustomers() {
        return database.getCustomer();
    }
}

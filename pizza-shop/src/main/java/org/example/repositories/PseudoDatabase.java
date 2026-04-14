package org.example.repositories;

import org.example.entities.Customers;
import org.example.entities.Order;
import org.example.entities.Pizza;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PseudoDatabase {
    private List<Pizza> pizzas;
    private List<Order> orders;
    private List<Customers> customer;

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Customers> getCustomer() {
        return customer;
    }

    public PseudoDatabase() {
        this.pizzas = List.of(
                new Pizza("Cheese Pizza", new BigDecimal("9.99"), 'L'),
                new Pizza("Cheese Pizza", new BigDecimal("7.99"), 'M'),
                new Pizza("Cheese Pizza", new BigDecimal("5.99"), 'S'),
                new Pizza("Pepperoni Pizza", new BigDecimal("10.99"), 'L'),
                new Pizza("Pepperoni Pizza", new BigDecimal("8.99"), 'M'),
                new Pizza("Pepperoni Pizza", new BigDecimal("6.99"), 'S')
        );
        this.orders = new ArrayList<>();
        this.customer = new ArrayList<>();


    }
}

package org.example;

import org.example.repositories.CustomerRepository;
import org.example.repositories.OrderRepository;
import org.example.repositories.PizzaRepository;
import org.example.repositories.PseudoDatabase;
import org.example.services.CustomerService;
import org.example.services.OrderService;
import org.example.services.PizzaService;

public class Main {
    static void main() {
        final PseudoDatabase database = new PseudoDatabase();
        final PizzaRepository pizzaRepository = new PizzaRepository(database);
        final OrderRepository orderRepository = new OrderRepository(database);
        final CustomerRepository customerRepository = new CustomerRepository(database);
        final PizzaService pizzaService = new PizzaService(pizzaRepository);
        final OrderService orderService = new OrderService(orderRepository);
        final CustomerService customerService = new CustomerService(customerRepository);

        pizzaService.displayPizza();
        orderService.displayOrders();
        customerService.displayCustomers();
    }
}

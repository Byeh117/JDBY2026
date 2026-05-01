package org.example;

import org.example.config.DatabaseConfig;
import org.example.model.Pizza;
import org.example.dao.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    static void main() {


        Connection connection = null;
        try {
            connection = DatabaseConfig.getInstance().getConn();
            System.out.println("DATABASE CONNECTION MADE!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JdbcPizzaRepository repository = new JdbcPizzaRepository(connection);
        repository.save(new Pizza("Cheese Pizza", new BigDecimal("9.99"), 'L'));
    }
}

//        final PseudoDatabase database = new PseudoDatabase();
//        final PizzaRepository pizzaRepository = new PizzaRepository(database);
//        final OrderRepository orderRepository = new OrderRepository(database);
//        final CustomerRepository customerRepository = new CustomerRepository(database);
//        final PizzaService pizzaService = new PizzaService(pizzaRepository);
//        final OrderService orderService = new OrderService(orderRepository);
//        final CustomerService customerService = new CustomerService(customerRepository);
//
//        pizzaService.displayPizza();
//        orderService.displayOrders();
//        customerService.displayCustomers();
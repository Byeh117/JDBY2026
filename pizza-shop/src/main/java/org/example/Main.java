package org.example;

import org.example.repositories.OrderRepository;
import org.example.repositories.PizzaRepository;
import org.example.repositories.PseudoDatabase;
import org.example.services.OrderService;
import org.example.services.PizzaService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        final PseudoDatabase database = new PseudoDatabase();
        final PizzaRepository pizzaRepository = new PizzaRepository(database);
        final OrderRepository orderRepository = new OrderRepository(database);
        final PizzaService pizzaService = new PizzaService(pizzaRepository);
        final OrderService orderService = new OrderService(orderRepository);

        orderService.displayOrders();
        pizzaService.displayPizza();
    }
}

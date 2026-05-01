package org.example.services;

import org.example.model.Order;
import org.example.dao.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(Order order) {
        orderRepository.save(order);
        System.out.println("Order placed: " + order);
    }

    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
        System.out.println("Order " + order.getOrderNumber() + " has been updated");
    }

    public void displayOrders() {
        orderRepository.getAllOrders().forEach((System.out::println));
    }
}

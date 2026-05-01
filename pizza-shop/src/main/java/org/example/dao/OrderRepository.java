package org.example.dao;

import org.example.model.Order;

import java.util.List;

public class OrderRepository {
    private final PseudoDatabase database;

    public OrderRepository(PseudoDatabase database) {
        this.database = database;
    }

    public void save(Order order) {
        database.getOrders().add(order);
    }

    public List<Order> getAllOrders() {
        return database.getOrders();
    }

    public void updateOrder(Order order) {
        for (Order databaseOrder : database.getOrders()) {
            if(databaseOrder.getOrderNumber() == order.getOrderNumber()) {
                databaseOrder.getOrderMap(order.getOrderMap());
                databaseOrder.getTotal();
            }
        }
    }
}

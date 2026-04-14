package org.example.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class Order {
    private long orderNumber;
    private LocalDateTime orderTimestamp;
    private Map<Pizza, Integer> orderMap;
    private BigDecimal total;

    public Order() {
    }

    public void getOrderMap(Object orderMap) {
    }

    public Order(
            long orderNumber,
            LocalDateTime orderTimestamp,
            Map<Pizza, Integer> orderMap
    ) {
        this.orderNumber = orderNumber;
        this.orderTimestamp = orderTimestamp;
        this.orderMap = orderMap;
        orderMap.forEach((piz, quan) ->
                this.total.
                        add((piz.getPrice().multiply(BigDecimal.valueOf(quan)))));
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(LocalDateTime orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public Map<Pizza, Integer> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Pizza, Integer> orderMap) {
        this.orderMap = orderMap;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        orderMap.forEach((piz, quan) ->
                this.total.
                        add((piz.getPrice().multiply(BigDecimal.valueOf(quan)))));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderNumber == order.orderNumber && Objects.equals(orderTimestamp, order.orderTimestamp) && Objects.equals(orderMap, order.orderMap) && Objects.equals(total, order.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, orderTimestamp, orderMap, total);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", orderTimestamp=" + orderTimestamp +
                ", orderMap=" + orderMap +
                ", total=" + total +
                '}';
    }
}

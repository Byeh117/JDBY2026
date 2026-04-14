package org.example.entities;

import java.util.List;
import java.util.Objects;

public class Customers {
    private String name;
    private long phone;
    private String address;
    private List<Order> orderList;

    public Customers() {}

    public Customers(String name, long phone, String address, List<Order> orderList) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.orderList = orderList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return phone == customers.phone && Objects.equals(name, customers.name) && Objects.equals(address, customers.address) && Objects.equals(orderList, customers.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, address, orderList);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", orderList=" + orderList +
                '}';
    }
}

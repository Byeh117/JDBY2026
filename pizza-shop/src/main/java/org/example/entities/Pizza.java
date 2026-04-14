package org.example.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Pizza {
    private String name;
    private BigDecimal price;
    private char size;

    public Pizza() {
        this.name = "Cheese Pizza";
        this.price = new BigDecimal("9.99");
        this.size = 'L';
    }

    public Pizza(String name, BigDecimal price, char size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return size == pizza.size && Objects.equals(name, pizza.name) && Objects.equals(price, pizza.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, size);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                '}';
    }
}

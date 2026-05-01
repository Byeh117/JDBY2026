package org.example.dao;

import org.example.model.Pizza;

import java.util.List;

public class PizzaRepository {
    private final PseudoDatabase database;

    public PizzaRepository(PseudoDatabase database) {
        this.database = database;
    }

    public List<Pizza> getAllPizzas() {
        return database.getPizzas();
    }
}

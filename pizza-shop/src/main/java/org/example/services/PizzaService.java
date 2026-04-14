package org.example.services;

import org.example.repositories.PizzaRepository;

public class PizzaService {
    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public void displayPizza() {
        System.out.println("Basic-Ass Pizza Shop");
        pizzaRepository.getAllPizzas().forEach((System.out::println));
    }
}

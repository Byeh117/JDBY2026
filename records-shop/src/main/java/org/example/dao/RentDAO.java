package org.example.dao;

import org.example.model.Rent;

import java.util.List;
import java.util.Optional;

public interface RentDAO {
    void save(Rent rent);
    Optional<Rent> findById(int id);
    List<Rent> findAll();
    void update(Rent rent);
    void deleteById(int id);
}
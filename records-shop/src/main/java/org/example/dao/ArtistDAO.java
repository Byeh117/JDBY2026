package org.example.dao;

import org.example.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistDAO {
    void save(Artist artist);
    Optional<Artist> findById(int id);
    List<Artist> findAll();
    void update(Artist artist);
    void deleteById(int id);
}
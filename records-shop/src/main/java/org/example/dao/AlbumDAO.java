package org.example.dao;

import org.example.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumDAO {
    void save(Album album);
    Optional<Album> findById(int id);
    Optional<Album> findByCatalogueId(String catalogueId);
    List<Album> findAll();
    void update(Album album);
    void deleteById(int id);
}

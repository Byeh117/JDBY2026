package org.example.services;

import org.example.dao.AlbumDAO;
import org.example.exception.ValidationException;
import org.example.model.Album;

import java.util.List;
import java.util.Optional;

public class AlbumService {
    private final AlbumDAO albumDAO;

    public AlbumService(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public Album addAlbum(Album album) throws ValidationException{
        validate(album);
        albumDAO.save(album);
        return album;
    }

    public List<Album> listAll() {
        return albumDAO.findAll();
    }

    public Optional<Album> findByCatalogueId(String catalogueId) {
        return albumDAO.findByCatalogueId(catalogueId);
    }

    public Album getById(int id) throws ValidationException{
        return albumDAO.findById(id)
                .orElseThrow(() -> new ValidationException(
                        "No album found with id " + id));
    }

    public Album updateAlbum(int id, Album updated) throws ValidationException{
        getById(id); // throws ValidationException if not found
        validate(updated);
        updated.setId(id);
        albumDAO.update(updated);
        return updated;
    }

    public void deleteAlbum(int id) throws ValidationException{
        getById(id); // throws ValidationException if not found
        albumDAO.deleteById(id);
    }

    private void validate(Album album) throws ValidationException{
        if (album.getTitle() == null || album.getTitle().isBlank())
            throw new ValidationException("Title is required");
        if (album.getCatalogueId() == null || album.getCatalogueId().length() < 5)
            throw new ValidationException("Catalogue Id must be at least 5 characters");
        if (album.getArtistId() <= 0)
            throw new ValidationException("A valid artist must be selected");
    }
}

package org.example.services;

import org.example.dao.ArtistDAO;
import org.example.exception.ValidationException;
import org.example.model.Artist;

import java.util.List;

public class ArtistService {
    private final ArtistDAO artistDAO;

    public ArtistService(ArtistDAO artistDAO) { this.artistDAO = artistDAO; }

    public Artist addArtist(Artist artist) throws ValidationException{
        validate(artist);
        artistDAO.save(artist);
        return artist;
    }

    public List<Artist> listAll() {
        return artistDAO.findAll();
    }

    public Artist getById(int id) throws ValidationException{
        return artistDAO.findById(id)
                .orElseThrow(() -> new ValidationException(
                        "No artist found with id " + id));
    }

    public Artist updateArtist(int id, Artist updated) throws ValidationException{
        getById(id); // throws ValidationException if not found
        validate(updated);
        updated.setId(id);
        artistDAO.update(updated);
        return updated;
    }

    public void deleteArtist(int id) throws ValidationException{
        getById(id); // throws ValidationException if not found
        artistDAO.deleteById(id);
    }

    private void validate(Artist artist) throws ValidationException{
        if (artist.getName() == null || artist.getName().isBlank())
            throw new ValidationException("Name is required");
    }
}

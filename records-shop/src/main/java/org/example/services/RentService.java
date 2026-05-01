package org.example.services;

import org.example.dao.RentDAO;
import org.example.dao.AlbumDAO;
import org.example.exception.ValidationException;
import org.example.model.Album;
import org.example.model.Rent;

import java.util.List;

public class RentService {
    private final RentDAO rentDAO;
    private final AlbumDAO albumDAO;

    public RentService(RentDAO rentDAO, AlbumDAO albumDAO) {
        this.rentDAO = rentDAO;
        this.albumDAO = albumDAO;
    }

    public Rent addRent(Rent rent) throws ValidationException {
        Album album = albumDAO.findById(rent.getAlbumId())
                .orElseThrow(() -> new ValidationException("No album found with id " + rent.getAlbumId()));
        if (!album.isAvailable())
            throw new ValidationException("Album is not available for rent");
        validate(rent);
        album.setAvailable(false);
        albumDAO.update(album);
        rentDAO.save(rent);
        return rent;
    }

    public List<Rent> listAll() {
        return rentDAO.findAll();
    }

    public Rent getById(int id) throws ValidationException {
        return rentDAO.findById(id)
                .orElseThrow(() -> new ValidationException("No rent found with id " + id));
    }

    public void returnAlbum(int rentId) throws ValidationException {
        Rent rent = getById(rentId);
        Album album = albumDAO.findById(rent.getAlbumId())
                .orElseThrow(() -> new ValidationException("No album found for this rent"));
        album.setAvailable(true);
        albumDAO.update(album);
        rentDAO.deleteById(rentId);
    }

    private void validate(Rent rent) throws ValidationException {
        if (rent.getAlbumId() <= 0)
            throw new ValidationException("A valid album must be selected");
        if (rent.getRenterName() == null || rent.getRenterName().isBlank())
            throw new ValidationException("Renter name is required");
        if (rent.getLoanDate() == null)
            throw new ValidationException("Loan date is required");
        if (rent.getReturnDate() != null && rent.getReturnDate().isBefore(rent.getLoanDate()))
            throw new ValidationException("Return date cannot be before loan date");
    }
}

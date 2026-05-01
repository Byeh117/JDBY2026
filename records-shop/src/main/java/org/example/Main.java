package org.example;

import org.example.dao.ArtistDAO;
import org.example.dao.impl.ArtistDAOImpl;
import org.example.exception.ValidationException;
import org.example.model.Artist;
import org.example.services.ArtistService;

import org.example.dao.AlbumDAO;
import org.example.dao.impl.AlbumDAOImpl;
import org.example.model.Album;
import org.example.services.AlbumService;

import org.example.dao.RentDAO;
import org.example.dao.impl.RentDAOImpl;
import org.example.model.Rent;
import org.example.services.RentService;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) throws ValidationException {
        // wire up dependencies
        ArtistDAO artistDAO = new ArtistDAOImpl();
        AlbumDAO albumDAO = new AlbumDAOImpl();
        RentDAO rentDAO = new RentDAOImpl();

        ArtistService artistService = new ArtistService(artistDAO);
        AlbumService albumService = new AlbumService(albumDAO);
        RentService rentService = new RentService(rentDAO, albumDAO);

        // create an artist first (master data)
        Artist artist = new Artist("Pink Floyd");
        artistService.addArtist(artist);
        System.out.println("Artist added: " + artist);

        Artist artist1 = new Artist("Metallica");
        artistService.addArtist(artist1);
        System.out.println("Artist added: " + artist1);

        Album album = albumService.findByCatalogueId("CAT001")
                .orElseGet(() -> {
                    Album a = new Album("Dark Side of the Moon", "CAT001", artist.getId(), true);
                    try {
                        albumService.addAlbum(a);
                    } catch (ValidationException e) {
                        throw new RuntimeException(e);
                    }
                    return a;
                });

        Album album1 = albumService.findByCatalogueId("CAT002")
                .orElseGet(() -> {
                    Album a = new Album("Ride the Lightning", "CAT001", artist.getId(), true);
                    try {
                        albumService.addAlbum(a);
                    } catch (ValidationException e) {
                        throw new RuntimeException(e);
                    }
                    return a;
                });

        // rent the album
        Rent rent = new Rent(album.getId(), "John Doe", LocalDate.now(), null);
        rentService.addRent(rent);
        System.out.println("Album rented: " + rent);

        // list everything
        System.out.println("\n--- Artists ---");
        artistService.listAll().forEach(System.out::println);

        System.out.println("\n--- Albums ---");
        albumService.listAll().forEach(System.out::println);

        System.out.println("\n--- Rents ---");
        rentService.listAll().forEach(System.out::println);

        // return the album
        rentService.returnAlbum(rent.getId());
        System.out.println("\nAlbum returned!");
    }
}
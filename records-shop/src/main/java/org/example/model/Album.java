package org.example.model;

import java.util.Objects;

public class Album {
    private int id;
    private String title;
    private String catalogueId;
    private int artistId;
    private boolean available;

    public Album() {}

    public Album(String title, String catalogueId, int artistId, boolean available) {
        this.title = title;
        this.catalogueId = catalogueId;
        this.artistId = artistId;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatalogueId() { return catalogueId; }

    public void setCatalogueId(String catalogueId) { this.catalogueId = catalogueId; }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id && artistId == album.artistId && available == album.available &&
                Objects.equals(title, album.title) && Objects.equals(catalogueId, album.catalogueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, catalogueId, artistId, available);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", catalogueId='" + catalogueId + '\'' +
                ", artistId=" + artistId +
                ", available=" + available +
                '}';
    }
}

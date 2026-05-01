package org.example.model;

import org.example.dao.ArtistDAO;

import java.time.LocalDate;
import java.util.Objects;

public class Rent {
    private int id;
    private int albumId;
    private String renterName;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Rent() {}

    public Rent(int albumId, String renterName, LocalDate loanDate, LocalDate returnDate) {
        this.albumId = albumId;
        this.renterName = renterName;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getAlbumId() { return albumId; }

    public void setAlbumId(int albumId) { this.albumId = albumId; }

    public String getRenterName() { return renterName; }

    public void setRenterName(String renterName) { this.renterName = renterName; }

    public LocalDate getLoanDate() { return loanDate; }

    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return id == rent.id && albumId == rent.albumId && Objects.equals(renterName, rent.renterName) && Objects.equals(loanDate, rent.loanDate) && Objects.equals(returnDate, rent.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumId, renterName, loanDate, returnDate);
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", renterName='" + renterName + '\'' +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

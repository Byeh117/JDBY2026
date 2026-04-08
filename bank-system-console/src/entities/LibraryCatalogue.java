package entities;

import java.math.BigDecimal;

public class LibraryCatalogue {
    private String bookTitle;
    private String bookGenre;
    private BigDecimal bookID;
    private boolean isAvailable;

    public LibraryCatalogue(String bookTitle, BigDecimal bookID, boolean isAvailable) {
        if (bookTitle == null || bookTitle.isEmpty()) { // pulled from claude.ai, checks if book exists in library
            throw new RuntimeException("Book title does not exist in database");
        }
        if (bookGenre == null || bookGenre.isEmpty()) { // pulled from claude.ai, checks if book exists in library
            throw new RuntimeException("Book genre does not exist in database");
        }
        if (bookID.compareTo(BigDecimal.ZERO) > 0) {
            throw new RuntimeException("Book ID must be greater than zero");
        }
        this.bookTitle = bookTitle;
        this.bookGenre = bookGenre;
        this.bookID = bookID;
        this.isAvailable = isAvailable;
    }

    // setters
    public void setBookTitle(String bookTitle) {
        if (bookTitle == null || bookTitle.isEmpty()) {
            throw new RuntimeException("Book title cannot be empty");
        }
        this.bookTitle = bookTitle;
    }

    public void setBookGenre(String bookGenre) {
        if (bookGenre == null || bookGenre.isEmpty()) {
            throw new RuntimeException("Book genre cannot be empty");
        }
        this.bookGenre = bookGenre;
    }

    public void setBookID(BigDecimal bookID) {
        if (bookID == null || bookID.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Book ID must be greater than zero");
        }
        this.bookID = bookID;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Checking out a book
    public void checkOut() {
        if (!isAvailable) {
            throw new RuntimeException("Book has already been checked out!");
        }
        this.isAvailable = false;
        System.out.println(bookTitle + " has already been checked out");
    }

    // Returning a book
    public void returnBook() {
        if (isAvailable) {
            throw new RuntimeException("Book is already in the library");
        }
        this.isAvailable = true;
        System.out.println(bookTitle + " has been returned");
    }

    // Provide book details
    public void printDetails() {
        System.out.println("=== Book Details ===");
        System.out.println("Title: " + bookTitle);
        System.out.println("Genre: " + bookGenre);
        System.out.println("ID: " + bookID);
        System.out.println("Available: " + isAvailable);
    }

    // Getters
    public String getBookTitle() { return bookTitle; }
    public String getBookGenre() { return bookGenre; }
    public BigDecimal getBookID() { return bookID; }
    public boolean getIsAvailable() { return isAvailable; }

    // Added with help of claude.ai, purpose is to identify the book's genre based on the first two digits of the book ID
    // Helper
    private String getGenre(BigDecimal bookID) {
        String idStr = bookID.toPlainString();
        if (idStr.length() < 2) {
            throw new RuntimeException("Book ID must be at least 2 digits long");
        }
        String prefix = idStr.substring(0,2); // reads the first two digits of the book ID
        switch (prefix) {
            case "10": return "Fiction";
            case "20": return "Non-Fiction";
            case "30": return "Science";
            case "40": return "History";
            case "50": return "Fantasy";
            case "60": return "Mystery";
            case "70": return "Biography";
            case "80": return "Anime";
            default: throw new RuntimeException("Unknown genre prefix: " + prefix + ". Please use a valid ID prefix"); // error message
        }
    }
}

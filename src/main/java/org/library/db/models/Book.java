package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "book")
@NamedQueries
({
    @NamedQuery( name = "Book.getByTitle",
            query = "SELECT b from Book b where b.title like concat('%',:title,'%')" ),

    @NamedQuery( name = "Book.getByGenre",
            query = "SELECT b from Book b where b.genre = :genreId" ),

    @NamedQuery( name = "Book.getByAuthor",
            query = "SELECT b from Book b where b.id = :authorId" )
})

public class Book extends Base {
    @Basic
    @Column(name = "shelf_id")
    private String shelfId;

    @ManyToOne
    @JoinColumn(name = "genre_id" )
    private Genre genre;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "language")
    private String language;

    @Basic
    @Column(name = "year")
    private int year;

    @Basic
    @Column(name = "amount")
    private short amount;

    @Basic
    @Column(name = "is_rare")
    private boolean isRare;

    public Book() {}

    public Book(String shelfId, Genre genre, String title,
                String language, int year, short amount, boolean isRare) {
        this.shelfId = shelfId;
        this.genre = genre;
        this.title = title;
        this.language = language;
        this.year = year;
        this.amount = amount;
        this.isRare = isRare;
    }

    public String getShelfId() {
        return shelfId;
    }
    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public short getAmount() {
        return amount;
    }
    public void setAmount(short amount) {
        this.amount = amount;
    }

    public boolean getIsRare() {
        return isRare;
    }
    public void setIsRare(boolean isRare) {
        this.isRare = isRare;
    }
}

package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "book")
@NamedQueries
({
    @NamedQuery( name = "Book.getByTitle",
            query = "SELECT b from Book b where b.title like concat('%',:title,'%')" )
})
public class Book extends Base {
    private String isbn;
    private String shelfId;
    private String title;
    private String language;
    private int year;
    private short amount;
    private byte onHand;
    private boolean is_rare;
    private Genre genre;

    @Id
    @Column(name = "ISBN")
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "is_rare")
    public boolean getIs_rare() {
        return is_rare;
    }
    public void setIs_rare(boolean is_rare) {
        this.is_rare = is_rare;
    }

    @ManyToOne
    @JoinColumn( name = "genre_id" )
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "shelf_id")
    public String getShelfId() {
        return shelfId;
    }
    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "amount")
    public short getAmount() {
        return amount;
    }
    public void setAmount(short amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "is_rare")
    public byte getOnHand() {
        return onHand;
    }
    public void setOnHand(byte onHand) {
        this.onHand = onHand;
    }
}

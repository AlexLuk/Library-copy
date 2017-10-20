package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class BookEntity {
    private String isbn;
    private String shelfId;
    private String title;
    private String language;
    private int year;
    private short amount;
    private byte onHand;
    private boolean is_rare;
    private GenreEntity genre;

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
    public GenreEntity getGenre() {
        return genre;
    }
    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "ISBN")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Id
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
    @Column(name = "on_hand")
    public byte getOnHand() {
        return onHand;
    }

    public void setOnHand(byte onHand) {
        this.onHand = onHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (year != that.year) return false;
        if (amount != that.amount) return false;
        if (onHand != that.onHand) return false;
        if (shelfId != null ? !shelfId.equals(that.shelfId) : that.shelfId != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shelfId != null ? shelfId.hashCode() : 0;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (int) amount;
        result = 31 * result + (int) onHand;
        return result;
    }
}

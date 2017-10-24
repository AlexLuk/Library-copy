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
    private String shelfId;
    private Genre genre;
    private String title;
    private String language;
    private Integer year;
    private Short amount;
    private Byte isRare;

    @Basic
    @Column(name = "shelf_id")
    public String getShelfId() {
        return shelfId;
    }
    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    @ManyToOne
    @JoinColumn(name = "genre_id" )
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
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
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "amount")
    public Short getAmount() {
        return amount;
    }
    public void setAmount(Short amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "is_rare")
    public Byte getIsRare() {
        return isRare;
    }
    public void setIsRare(Byte isRare) {
        this.isRare = isRare;
    }
}

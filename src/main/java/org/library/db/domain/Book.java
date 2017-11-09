package org.library.db.domain;

import javax.persistence.*;

@Entity
@Table(name = "book")
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
    private Integer year;

    @Basic
    @Column(name = "amount")
    private Short amount;

    @Basic
    @Column(name = "is_rare")
    private Boolean isRare;

    public Book() {}

    public Book(String shelfId, Genre genre, String title,
                String language, Integer year, Short amount, Boolean isRare) {
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

    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

    public Short getAmount() {
        return amount;
    }
    public void setAmount(Short amount) {
        this.amount = amount;
    }

    public Boolean getIsRare() {
        return isRare;
    }
    public void setIsRare(Boolean isRare) {
        this.isRare = isRare;
    }
}

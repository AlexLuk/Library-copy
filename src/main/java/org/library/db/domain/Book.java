package org.library.db.domain;

import com.google.common.collect.Lists;
import org.library.misc.Utils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "book")
public class Book extends Base {
    //todo add annotations for serialisation

    @Basic
    @Column(name = "shelfCode")
    private String shelfCode;

    @Enumerated(EnumType.ORDINAL)
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

    public Book() {
    }

    @ManyToMany
    @JoinTable(name = "author_book",
        joinColumns = @JoinColumn(name = "book"),
        inverseJoinColumns = @JoinColumn(name = "author")
    )
    @OrderBy("last_name")
    private List<Author> authors = new ArrayList<>();

    public Book(String shelfCode, Genre genre, String title,
                String language, Integer year, Short amount, Boolean isRare) {
        this.shelfCode = shelfCode;
        this.genre = genre;
        this.title = title;
        this.language = language;
        this.year = year;
        this.amount = amount;
        this.isRare = isRare;
    }

    public String getShelfCode() {
        return shelfCode;
    }
    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public enum Genre {
        EMPTY (new HashMap<String, String>()
        {{
            put("", "");
            put("", "");
        }}),
        COMEDY (new HashMap<String, String>()
        {{
            put("en", "comedy");
            put("ru", "комедия");
        }}),
        DRAMA (new HashMap<String, String>()
        {{
            put("en", "drama");
            put("ru", "драма");
        }}),
        ROMANCE (new HashMap<String, String>()
        {{
            put("en", "romance");
            put("ru", "романтика");
        }}),

        SATIRE (new HashMap<String, String>()
        {{
            put("en", "satire");
            put("ru", "сатира");
        }}),

        TRAGEDY (new HashMap<String, String>()
        {{
            put("en", "tragedy");
            put("ru", "трагедия");
        }}),

        FANTASY (new HashMap<String, String>()
        {{
            put("en", "fantasy");
            put("ru", "фантастика");
        }}),

        NOVEL (new HashMap<String, String>()
        {{
            put("en", "novel");
            put("ru", "роман");
        }});

        private final HashMap<String, String> vals;

        Genre(HashMap<String, String> vals) {
            this.vals = vals;
        }

        public HashMap<String, String> getVals() {
            return vals;
        }
    }
}


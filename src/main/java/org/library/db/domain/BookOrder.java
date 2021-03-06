package org.library.db.domain;

import javax.persistence.*;

@Entity
@Table(name = "book_order")
public class BookOrder extends Base {
    @ManyToOne
    @JoinColumn(name = "reader")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book")
    private Book book;

    @Basic
    @Column(name = "on_hands")
    private Boolean onHands;

    public BookOrder() {
    }

    public BookOrder(Reader reader, Book book, boolean onHands) {
        this.reader = reader;
        this.book = book;
        this.onHands = onHands;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Boolean getOnHands() {
        return onHands;
    }

    public void setOnHands(Boolean onHands) {
        this.onHands = onHands;
    }
}
